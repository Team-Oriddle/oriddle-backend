package com.tuk.oriddle.domain.quizroom.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("quizStatus")
class QuizRoomProgressStatus(
    quizRoomId: Long,
    questionCount: Long,
    isQuestionOpen: Boolean = false,
    currentQuestionNumber: Long = 1
) {
    @Id
    val quizRoomId: Long = quizRoomId
    private val questionCount: Long = questionCount
    val isQuestionOpen: Boolean = isQuestionOpen
    val currentQuestionNumber: Long = currentQuestionNumber

    fun getQuestionOpenStatus(): QuizRoomProgressStatus {
        return QuizRoomProgressStatus(quizRoomId, questionCount, true, currentQuestionNumber)
    }

    fun getNextQuestionStatus(): QuizRoomProgressStatus {
        return QuizRoomProgressStatus(quizRoomId, questionCount, false, currentQuestionNumber + 1)
    }

    fun isLastQuestion(): Boolean {
        return currentQuestionNumber == questionCount
    }
}