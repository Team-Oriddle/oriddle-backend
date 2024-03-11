package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.answer.service.AnswerRedisService
import com.tuk.oriddle.domain.participant.service.ParticipantRedisService
import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.service.QuestionQueryService
import com.tuk.oriddle.domain.question.service.QuestionRedisService
import com.tuk.oriddle.domain.quizroom.dto.message.CheckAnswerMessage
import com.tuk.oriddle.domain.quizroom.scheduler.QuizRoomScheduler
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Value
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
    private val quizRoomScheduler: QuizRoomScheduler,
    @Value("\${config.quiz-room.start-wait-time}") private val startWaitTime: Long,
    @Value("\${config.quiz-room.default-wait-time}") private val defaultWaitTime: Long
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
        quizRoomScheduler.scheduleQuestionPublish(quizRoomId, startWaitTime)
    }

    fun checkAnswer(quizRoomId: Long, answerMessage: CheckAnswerMessage, userId: Long) {
        val status = quizRoomRedisService.getQuizStatus(quizRoomId)
        val number = status.currentQuestionNumber
        val score = questionRedisService.getQuestion(quizRoomId, number).score
        val isAnswerCorrect = answerRedisService.isAnswerCorrect(answerMessage, quizRoomId, number)
        if (isAnswerCorrect) {
            quizRoomMessageService.sendAnswerCorrectMessage(
                quizRoomId,
                userId,
                answerMessage,
                score
            )
            val nextStatus = status.getNextQuestionStatus()
            if (nextStatus.isQuizRoomFinish()) {
                // TODO: 퀴즈 종료 로직 구현
                return
            }
            quizRoomRedisService.saveQuizStatus(nextStatus)
            quizRoomScheduler.scheduleQuestionPublish(quizRoomId, defaultWaitTime)
        }
    }
}