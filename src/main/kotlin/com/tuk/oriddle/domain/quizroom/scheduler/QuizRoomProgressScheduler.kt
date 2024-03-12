package com.tuk.oriddle.domain.quizroom.scheduler

import com.tuk.oriddle.domain.question.service.QuestionRedisService
import com.tuk.oriddle.domain.quizroom.dto.message.QuestionMessage
import com.tuk.oriddle.domain.quizroom.dto.message.QuestionTimeOutMessage
import com.tuk.oriddle.domain.quizroom.entity.QuizRoomProgressStatus
import com.tuk.oriddle.domain.quizroom.service.QuizRoomMessageService
import com.tuk.oriddle.domain.quizroom.service.QuizRoomRedisService
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Component

@Component
@EnableAsync
class QuizRoomProgressScheduler(
    private val quizRoomMessageService: QuizRoomMessageService,
    private val quizRoomRedisService: QuizRoomRedisService,
    private val questionRedisService: QuestionRedisService,
    @Value("\${config.quiz-room.start-wait-time}") private val startWaitTime: Int,
    @Value("\${config.quiz-room.question-wait-time}") private val questionWaitTime: Int
) {
    @Async
    fun scheduleStartQuestionPublish(quizRoomId: Long) {
        Thread.sleep(startWaitTime.toMilliSec())
        publishQuestion(quizRoomId)
    }

    @Async
    fun scheduleNextQuestionPublish(quizRoomId: Long) {
        Thread.sleep(questionWaitTime.toMilliSec())
        publishQuestion(quizRoomId)
    }

    @Async
    fun scheduleQuestionTimeOut(quizRoomId: Long, timeLimit: Int) {
        val previousStatus = quizRoomRedisService.getQuizStatus(quizRoomId)
        Thread.sleep(timeLimit.toMilliSec())
        val newStatus = quizRoomRedisService.getQuizStatus(quizRoomId)
        if (previousStatus == newStatus) {
            val number = newStatus.currentQuestionNumber
            val answer = questionRedisService.getQuestion(quizRoomId, number).mainAnswer!!
            quizRoomMessageService.sendTimeOutMessage(quizRoomId, QuestionTimeOutMessage(answer))
            progressNextQuestionOrFinishQuiz(quizRoomId, newStatus)
        }
    }

    private fun publishQuestion(quizRoomId: Long) {
        val status = quizRoomRedisService.getQuizStatus(quizRoomId)
        val newQuizRoomProgressStatus = status.getQuestionOpenStatus()
        quizRoomRedisService.saveQuizStatus(newQuizRoomProgressStatus)
        val currentQuestionNumber = newQuizRoomProgressStatus.currentQuestionNumber
        val currentQuestion = questionRedisService.getQuestion(quizRoomId, currentQuestionNumber)
        val questionMessage = QuestionMessage.of(currentQuestion)
        quizRoomMessageService.sendQuestionMessage(quizRoomId, questionMessage)
        val timeLimit = currentQuestion.timeLimit
        scheduleQuestionTimeOut(quizRoomId, timeLimit)
    }

    // TODO: 공통 로직을 다루는 모듈을 만들어서 처리하도록 수정
    private fun progressNextQuestionOrFinishQuiz(quizRoomId: Long, status: QuizRoomProgressStatus) {
        if (status.isLastQuestion()) {
            // TODO: 퀴즈 종료 로직 구현
        } else {
            quizRoomRedisService.saveQuizStatus(status.getNextQuestionStatus())
            scheduleNextQuestionPublish(quizRoomId)
        }
    }

    private fun Int.toMilliSec() = (this * 1000).toLong()
}