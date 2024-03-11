package com.tuk.oriddle.domain.participant.repository

import com.tuk.oriddle.domain.participant.dto.redis.ParticipantsRedisDto
import org.springframework.data.repository.CrudRepository

interface ParticipantRedisRepository : CrudRepository<ParticipantsRedisDto, Long> {
}