package com.tuk.oriddle.domain.quizroom.dto.response

import com.tuk.oriddle.domain.participant.dto.ParticipantGetInfoResponse
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom

data class QuizRoomGetInfoResponse(
    val roomTitle: String,
    val quizTitle: String,
    val maxParticipant: Int,
    val participators: List<ParticipantGetInfoResponse>
) {
    companion object {
        fun of(
            quizRoom: QuizRoom,
            participators: List<ParticipantGetInfoResponse>
        ): QuizRoomGetInfoResponse {
            return QuizRoomGetInfoResponse(
                roomTitle = quizRoom.title,
                quizTitle = quizRoom.quiz.title,
                maxParticipant = quizRoom.maxParticipant,
                participators = participators
            )
        }
    }
}
