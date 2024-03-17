package com.tuk.oriddle.domain.quizroom.controller

import com.tuk.oriddle.domain.quizroom.dto.message.ChatReceiveMessage
import com.tuk.oriddle.domain.quizroom.dto.message.CheckAnswerMessage
import com.tuk.oriddle.domain.quizroom.service.QuizRoomProgressService
import com.tuk.oriddle.global.result.ResultCode.*
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class QuizRoomWsController(private val quizRoomProgressService: QuizRoomProgressService) {
    @MessageMapping("/quiz-room/{quizRoomId}/check-answer")
    fun handleMessage(
        @DestinationVariable("quizRoomId") quizRoomId: Long,
        @Payload message: CheckAnswerMessage,
        headerAccessor: SimpMessageHeaderAccessor
    ) {
        val userId = (headerAccessor.sessionAttributes?.get("id") as String).toLong()
        quizRoomProgressService.checkAnswer(quizRoomId, message, userId)
    }

    @MessageMapping("quiz-room/{quizRoomId}/chat")
    fun sendChatMessage(
        @DestinationVariable("quizRoomId") quizRoomId: Long,
        @Payload message: ChatReceiveMessage,
        headerAccessor: SimpMessageHeaderAccessor
    ) {
        val userId = (headerAccessor.sessionAttributes?.get("id") as String).toLong()
        quizRoomProgressService.sendChatMessage(quizRoomId, message, userId)
    }
}