package com.tuk.oriddle.domain.quizroom.scheduler

import com.tuk.oriddle.domain.question.service.QuestionRedisService
import com.tuk.oriddle.domain.quizroom.dto.message.QuestionMessage
import com.tuk.oriddle.domain.quizroom.service.QuizRoomMessageService
import com.tuk.oriddle.domain.quizroom.service.QuizRoomRedisService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Component

@Component
@EnableAsync
class QuizRoomScheduler(
    private val quizRoomMessageService: QuizRoomMessageService,
    private val quizRoomRedisService: QuizRoomRedisService,
    private val questionRedisService: QuestionRedisService,
) {
    // TODO: 문제 제한시간이 다 되면 메시지를 보내고 상태를 변경하는 로직 추가
    @Async
    fun scheduleQuestionPublish(quizRoomId: Long, waitTime: Long) {
        Thread.sleep(secToMilliSec(waitTime))
        val quizStatusRedisDto = quizRoomRedisService.getQuizStatus(quizRoomId).getQuestionOpenStatus()
        quizRoomRedisService.saveQuizStatus(quizStatusRedisDto)
        val currentQuestionNumber = quizStatusRedisDto.currentQuestionNumber
        val currentQuestionRedisDto = questionRedisService.getQuestion(quizRoomId, currentQuestionNumber)
        val questionMessage = QuestionMessage.of(currentQuestionRedisDto)
        quizRoomMessageService.sendQuestionMessage(quizRoomId, questionMessage)
    }

    private fun secToMilliSec(sec: Long): Long {
        return sec * 1000
    }
}