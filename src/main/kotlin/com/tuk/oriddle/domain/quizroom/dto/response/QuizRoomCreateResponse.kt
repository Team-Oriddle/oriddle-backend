package com.tuk.oriddle.domain.quizroom.dto.response

import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import java.time.LocalDateTime

data class QuizRoomCreateResponse (
    val quizRoomId: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(
                quizRoom: QuizRoom
        ): QuizRoomCreateResponse {
            return QuizRoomCreateResponse(
                    quizRoomId = quizRoom.id,
                    createdAt = quizRoom.createdAt
            )
        }
    }
}