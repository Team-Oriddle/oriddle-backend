package com.tuk.oriddle.domain.quizroom.dto.response

data class QuizRoomJoinResponse(
    val quizRoomId: Long,
    val userId: Long
) {
    companion object {
        fun of(
            quizRoomId: Long,
            userId: Long
        ): QuizRoomJoinResponse {
            return QuizRoomJoinResponse(
                quizRoomId = quizRoomId,
                userId = userId
            )
        }
    }
}