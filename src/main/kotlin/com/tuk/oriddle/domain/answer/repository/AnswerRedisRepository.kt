package com.tuk.oriddle.domain.answer.repository

import com.tuk.oriddle.domain.answer.dto.redis.AnswerRedisDto
import org.springframework.data.repository.CrudRepository

interface AnswerRedisRepository : CrudRepository<AnswerRedisDto, String> {
}