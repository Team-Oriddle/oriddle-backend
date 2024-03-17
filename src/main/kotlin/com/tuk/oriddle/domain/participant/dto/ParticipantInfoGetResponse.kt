package com.tuk.oriddle.domain.participant.dto

import com.tuk.oriddle.domain.participant.entity.Participant

data class ParticipantInfoGetResponse(
    val userId: Long,
    val position: Int,
    val nickname: String,
    val isHost: Boolean
) {
    companion object {
        fun of(
            participant: Participant
        ): ParticipantInfoGetResponse {
            return ParticipantInfoGetResponse(
                userId = participant.user.id,
                position = participant.position,
                nickname = participant.user.nickname,
                isHost = participant.isHost
            )
        }
    }
}
