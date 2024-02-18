package com.tuk.oriddle.domain.quizroom.dto.response

data class QuizRoomCreateResponse(
    val quizRoomId: Long,
) {
    companion object {
        fun of(
            quizRoomId: Long
        ): QuizRoomCreateResponse {
            return QuizRoomCreateResponse(
                quizRoomId = quizRoomId
            )
        }
    }
}