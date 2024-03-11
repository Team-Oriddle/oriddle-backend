package com.tuk.oriddle.domain.answer.dto.redis

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("answer")
data class AnswerRedisDto(
    @Id val key: String,
    val values: MutableSet<String>
)