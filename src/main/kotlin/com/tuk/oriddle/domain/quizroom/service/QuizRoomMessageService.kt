package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.quizroom.dto.message.JoinQuizRoomMessage
import com.tuk.oriddle.domain.quizroom.dto.message.LeaveQuizRoomMessage
import com.tuk.oriddle.domain.quizroom.dto.message.QuestionMessage
import com.tuk.oriddle.domain.quizroom.dto.message.StartQuizRoomMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class QuizRoomMessageService(
    private val messagingTemplate: SimpMessagingTemplate,
    @Value("\${config.quiz-room.start-wait-time}")
    private val startWaitTime: Long
) {
    fun sendQuizRoomJoinMessage(quizRoomId: Long, userId: Long, nickname: String, position: Int) {
        messagingTemplate.convertAndSend(
            "/topic/quiz-room/$quizRoomId/join",
            JoinQuizRoomMessage(userId, nickname, position)
        )
    }

    fun sendQuizRoomLeaveMessage(quizRoomId: Long, userId: Long) {
        messagingTemplate.convertAndSend(
            "/topic/quiz-room/$quizRoomId/leave",
            LeaveQuizRoomMessage(userId)
        )
    }

    fun sendQuizRoomStartMessage(quizRoomId: Long) {
        messagingTemplate.convertAndSend(
            "/topic/quiz-room/$quizRoomId/start",
            StartQuizRoomMessage(startWaitTime)
        )
    }

    fun sendQuestionMessage(quizRoomId: Long, questionMessage: QuestionMessage) {
        messagingTemplate.convertAndSend("/topic/quiz-room/$quizRoomId/question", questionMessage)
    }
}