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
        sendMessage("join", quizRoomId, JoinQuizRoomMessage(userId, nickname, position))
    }

    fun sendQuizRoomLeaveMessage(quizRoomId: Long, userId: Long) {
        sendMessage("leave", quizRoomId, LeaveQuizRoomMessage(userId))
    }

    fun sendQuizRoomStartMessage(quizRoomId: Long) {
        sendMessage("start", quizRoomId, StartQuizRoomMessage(startWaitTime))
    }

    fun sendQuestionMessage(quizRoomId: Long, questionMessage: QuestionMessage) {
        sendMessage("question", quizRoomId, questionMessage)
    }

    fun sendAnswerCorrectMessage(
        quizRoomId: Long,
        userId: Long,
        answerMessage: CheckAnswerMessage,
        score: Int
    ) {
        // TODO: 다음 문제로 넘어가는 처리 구현
        val correctMessage = AnswerCorrectMessage(userId, answerMessage.answer, score)
        sendMessage("answer", quizRoomId, correctMessage)
    }

    fun sendTimeOutMessage(quizRoomId: Long, timeOutMessage: QuestionTimeOutMessage) {
        sendMessage("time-out", quizRoomId, timeOutMessage)
    }

    private fun sendMessage(topic: String, quizRoomId: Long, message: Any) {
        val destination = "/topic/quiz-room/$quizRoomId/$topic"
        messagingTemplate.convertAndSend(destination, message)
    }
}