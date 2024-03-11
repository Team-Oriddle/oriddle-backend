package com.tuk.oriddle.domain.question.repository

import com.tuk.oriddle.domain.question.dto.redis.QuestionRedisDto
import org.springframework.data.repository.CrudRepository

interface QuestionRedisRepository : CrudRepository<QuestionRedisDto, String> {
}