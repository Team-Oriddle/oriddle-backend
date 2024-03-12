package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.answer.service.AnswerRedisService
import com.tuk.oriddle.domain.participant.service.ParticipantRedisService
import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.service.QuestionQueryService
import com.tuk.oriddle.domain.question.service.QuestionRedisService
import com.tuk.oriddle.domain.quizroom.dto.message.CheckAnswerMessage
import com.tuk.oriddle.domain.quizroom.entity.QuizRoomProgressStatus
import com.tuk.oriddle.domain.quizroom.scheduler.QuizRoomProgressScheduler
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QuizRoomProgressService(
    private val quizRoomQueryService: QuizRoomQueryService,
    private val questionQueryService: QuestionQueryService,
    private val answerRedisService: AnswerRedisService,
    private val participantRedisService: ParticipantRedisService,
    private val quizRoomRedisService: QuizRoomRedisService,
    private val questionRedisService: QuestionRedisService,
    private val quizRoomMessageService: QuizRoomMessageService,
    private val quizRoomProgressScheduler: QuizRoomProgressScheduler,
) {
    @Transactional
    fun startQuizRoom(quizRoomId: Long) {
        // TODO: 쿼리 최적화 하기
        val quizRoom = quizRoomQueryService.findById(quizRoomId)
        val questions = questionQueryService.findByQuizId(quizRoom.quiz.id) as MutableList<Question>
        val questionCount = questions.size.toLong()
        quizRoomRedisService.saveQuizStatus(quizRoomId, questionCount)
        participantRedisService.saveParticipants(quizRoomId, quizRoom.participants)
        for (question in questions) {
            answerRedisService.saveAnswers(quizRoomId, question.number, question.getAnswerSet())
            questionRedisService.saveQuestion(quizRoomId, question.number, question)
        }
        quizRoomMessageService.sendQuizRoomStartMessage(quizRoomId)
        quizRoomProgressScheduler.scheduleQuizRoomStart(quizRoomId)
    }

    fun checkAnswer(quizRoomId: Long, answerMessage: CheckAnswerMessage, userId: Long): Boolean {
        val status = quizRoomRedisService.getQuizStatus(quizRoomId)
        if (!status.isQuestionOpen) return false
        val number = status.currentQuestionNumber
        if (answerRedisService.isAnswerCorrect(answerMessage, quizRoomId, number)) {
            val score = questionRedisService.getQuestion(quizRoomId, number).score
            quizRoomMessageService.sendAnswerCorrectMessage(
                quizRoomId,
                userId,
                answerMessage,
                score
            )
            processNextQuestionOrFinishQuiz(quizRoomId, status)
        }
        return true
    }

    private fun processNextQuestionOrFinishQuiz(quizRoomId: Long, status: QuizRoomProgressStatus) {
        if (status.isLastQuestion()) {
            // TODO: 퀴즈 종료 로직 구현
        } else {
            quizRoomProgressScheduler.scheduleNextQuestionOpen(quizRoomId, status)
        }
    }
}