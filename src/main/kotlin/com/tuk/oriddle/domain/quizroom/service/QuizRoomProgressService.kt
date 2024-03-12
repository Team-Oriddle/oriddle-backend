package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.answer.service.AnswerRedisService
import com.tuk.oriddle.domain.participant.exception.ParticipantNotHostException
import com.tuk.oriddle.domain.participant.service.ParticipantQueryService
import com.tuk.oriddle.domain.participant.service.ParticipantRedisService
import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.service.QuestionQueryService
import com.tuk.oriddle.domain.question.service.QuestionRedisService
import com.tuk.oriddle.domain.quizroom.dto.message.CheckAnswerMessage
import com.tuk.oriddle.domain.quizroom.entity.QuizRoomProgressStatus
import com.tuk.oriddle.domain.quizroom.scheduler.QuizRoomProgressScheduler
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
    private val participantQueryService: ParticipantQueryService
) {
    fun startQuizRoom(quizRoomId: Long, userId: Long) {
        val isNotHost =
            !participantQueryService.findByQuizRoomIdAndUserId(quizRoomId, userId).isHost
        if (isNotHost) {
            throw ParticipantNotHostException()
        }
        initQuizRoomProgressData(quizRoomId)
        quizRoomMessageService.sendQuizRoomStartMessage(quizRoomId)
        quizRoomProgressScheduler.scheduleStartQuestionPublish(quizRoomId)
    }

    private fun initQuizRoomProgressData(quizRoomId: Long) {
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
    }

    fun checkAnswer(quizRoomId: Long, answer: CheckAnswerMessage, userId: Long) {
        val status = quizRoomRedisService.getQuizStatus(quizRoomId)
        if (!status.isQuestionOpen) {
            return
        }
        val number = status.currentQuestionNumber
        val isCorrect = answerRedisService.isAnswerCorrect(answer, quizRoomId, number)
        if (isCorrect) {
            val score = questionRedisService.getQuestion(quizRoomId, number).score
            quizRoomMessageService.sendAnswerCorrectMessage(quizRoomId, userId, answer, score)
            progressNextQuestionOrFinishQuiz(quizRoomId, status)
        }
    }

    // TODO: 공통 로직을 다루는 모듈을 만들어서 처리하도록 수정
    private fun progressNextQuestionOrFinishQuiz(quizRoomId: Long, status: QuizRoomProgressStatus) {
        if (status.isLastQuestion()) {
            quizRoomMessageService.sendFinishMessage(quizRoomId)
        } else {
            quizRoomRedisService.saveQuizStatus(status.getNextQuestionStatus())
            quizRoomProgressScheduler.scheduleNextQuestionPublish(quizRoomId)
        }
    }
}