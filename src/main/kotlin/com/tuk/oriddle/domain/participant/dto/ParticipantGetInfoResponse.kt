package com.tuk.oriddle.domain.participant.dto

import com.tuk.oriddle.domain.participant.entity.Participant

data class ParticipantGetInfoResponse(
    val userId: Long,
    val position: Int,
    val nickname: String
) {
    companion object {
        fun of(
            participant: Participant
        ): ParticipantGetInfoResponse {
            return ParticipantGetInfoResponse(
                userId =  participant.user.id,
                position = participant.position,
                nickname = participant.user.nickname
            )
        }
    }
}
