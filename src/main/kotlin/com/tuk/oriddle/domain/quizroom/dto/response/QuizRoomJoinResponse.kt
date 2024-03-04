package com.tuk.oriddle.domain.quizroom.dto.response

data class QuizRoomJoinResponse(
    val quizRoomId: Long,
    val userId: Long,
    val position: Int
) {
    companion object {
        fun of(
            quizRoomId: Long,
            userId: Long,
            position: Int
        ): QuizRoomJoinResponse {
            return QuizRoomJoinResponse(
                quizRoomId = quizRoomId,
                userId = userId,
                position = position
            )
        }
    }
}