package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.quizroom.dto.message.*
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

    fun sendAnswerCorrectMessage(
        quizRoomId: Long,
        userId: Long,
        answerMessage: CheckAnswerMessage,
        score: Int
    ) {
        val answerCorrectMessage = AnswerCorrectMessage(userId, answerMessage.answer, score)
        messagingTemplate.convertAndSend(
            "/topic/quiz-room/$quizRoomId/answer",
            answerCorrectMessage
        )
        // TODO: 다음 문제로 넘어가는 처리 구현
    }
}