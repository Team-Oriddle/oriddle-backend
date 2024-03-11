package com.tuk.oriddle.domain.participant.dto.redis

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("participants")
data class ParticipantsRedisDto(
    @Id val quizRoomId: Long,
    val participants: MutableList<ParticipantRedisDto>
)