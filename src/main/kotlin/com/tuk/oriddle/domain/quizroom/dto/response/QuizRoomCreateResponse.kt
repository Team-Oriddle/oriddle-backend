package com.tuk.oriddle.domain.quizroom.dto.response

data class QuizRoomCreateResponse(
    val quizRoomId: Long,
    val position: Int
) {
    companion object {
        fun of(
            quizRoomId: Long,
            position: Int
        ): QuizRoomCreateResponse {
            return QuizRoomCreateResponse(
                quizRoomId = quizRoomId,
                position = position
            )
        }
    }
}