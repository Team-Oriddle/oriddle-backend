package com.tuk.oriddle.domain.quizroom.dto.response

import com.tuk.oriddle.domain.participant.dto.ParticipantInfoGetResponse
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom

data class QuizRoomInfoGetResponse(
    val roomTitle: String,
    val quizTitle: String,
    val maxParticipant: Int,
    val participants: List<ParticipantInfoGetResponse>
) {
    companion object {
        fun of(
            quizRoom: QuizRoom,
            participants: List<ParticipantInfoGetResponse>
        ): QuizRoomInfoGetResponse {
            return QuizRoomInfoGetResponse(
                roomTitle = quizRoom.title,
                quizTitle = quizRoom.quiz.title,
                maxParticipant = quizRoom.maxParticipant,
                participants = participants
            )
        }
    }
}
