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
    // TODO: 문제 제한시간이 다 되면 메시지를 보내고 상태를 변경하는 로직 추가
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

    private fun publishQuestion(quizRoomId: Long) {
        val status = quizRoomRedisService.getQuizStatus(quizRoomId)
        val newQuizRoomProgressStatus = status.getQuestionOpenStatus()
        quizRoomRedisService.saveQuizStatus(newQuizRoomProgressStatus)
        val currentQuestionNumber = newQuizRoomProgressStatus.currentQuestionNumber
        val currentQuestion = questionRedisService.getQuestion(quizRoomId, currentQuestionNumber)
        val questionMessage = QuestionMessage.of(currentQuestion)
        quizRoomMessageService.sendQuestionMessage(quizRoomId, questionMessage)
    }

    private fun Int.toMilliSec() = (this * 1000).toLong()
}