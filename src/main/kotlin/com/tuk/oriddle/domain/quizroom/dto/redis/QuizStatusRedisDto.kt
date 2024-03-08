package com.tuk.oriddle.domain.quizroom.dto.redis

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.tuk.oriddle.domain.quizroom.entity.QuizProgressStatus

data class QuizStatusRedisDto @JsonCreator constructor(
    @JsonProperty("quizId") val quizId: Long,
    @JsonProperty("questionCount") val questionCount: Long,
    @JsonProperty("status") val status: QuizProgressStatus = QuizProgressStatus.WAIT,
    @JsonProperty("currentQuestionNumber") val currentQuestionNumber: Long = 1
)
