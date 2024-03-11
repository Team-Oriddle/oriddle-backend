package com.tuk.oriddle.domain.participant.dto.redis

data class ParticipantRedisDto(
    val userId: Long,
    val position: Int,
    val score: Int = 0
)