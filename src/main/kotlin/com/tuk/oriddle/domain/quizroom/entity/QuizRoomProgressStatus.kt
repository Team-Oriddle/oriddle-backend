package com.tuk.oriddle.domain.quizroom.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("quizStatus")
data class QuizRoomProgressStatus(
    @Id val quizRoomId: Long,
    private val questionCount: Long,
    val isQuestionOpen: Boolean = false,
    val currentQuestionNumber: Long = 1
) {
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