package com.tuk.oriddle.domain.quizroom.scheduler

import com.tuk.oriddle.domain.question.service.QuestionRedisService
import com.tuk.oriddle.domain.quizroom.dto.message.QuestionMessage
import com.tuk.oriddle.domain.quizroom.service.QuizRoomMessageService
import com.tuk.oriddle.domain.quizroom.service.QuizRoomRedisService
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Component

@Component
@EnableAsync
class QuizRoomScheduler(
    private val quizRoomMessageService: QuizRoomMessageService,
    private val quizRoomRedisService: QuizRoomRedisService,
    private val questionRedisService: QuestionRedisService,
    @Value("\${config.quiz-room.start-wait-time}")
    private val startWaitTime: Long
) {
    @Async
    fun scheduleQuestionPublish(quizRoomId: Long) {
        Thread.sleep(secToMilliSec(startWaitTime))
        val quizStatusRedisDto = quizRoomRedisService.getQuizStatus(quizRoomId)
        val currentQuestionNumber = quizStatusRedisDto.currentQuestionNumber
        val currentQuestionRedisDto = questionRedisService.getQuestion(quizRoomId, currentQuestionNumber)
        val questionMessage = QuestionMessage.of(currentQuestionRedisDto)
        quizRoomMessageService.sendQuestionMessage(quizRoomId, questionMessage)
        // TODO: 문제 제한시간이 다 되면 메시지를 발송하는 기능 구현하기
    }

    private fun secToMilliSec(sec: Long): Long {
        return sec * 1000
    }
}