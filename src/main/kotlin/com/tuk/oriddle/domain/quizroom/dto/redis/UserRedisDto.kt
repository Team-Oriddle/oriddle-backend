package com.tuk.oriddle.domain.quizroom.dto.redis

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class UserRedisDto @JsonCreator constructor(
    @JsonProperty("userId") val userId: Long,
    @JsonProperty("position") val position: Int,
    @JsonProperty("score") val score: Int = 0
)