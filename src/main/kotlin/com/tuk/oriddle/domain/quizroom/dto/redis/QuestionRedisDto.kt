package com.tuk.oriddle.domain.quizroom.dto.redis

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.entity.QuestionSourceType
import com.tuk.oriddle.domain.question.entity.QuestionType

data class QuestionRedisDto @JsonCreator constructor(
    @JsonProperty("number") val number: Long,
    @JsonProperty("description") val description: String,
    @JsonProperty("type") val type: QuestionType,
    @JsonProperty("sourceType") val sourceType: QuestionSourceType,
    @JsonProperty("source") val source: String?,
    @JsonProperty("score") val score: Int,
    @JsonProperty("timeLimit") val timeLimit: Int,
    @JsonProperty("mainAnswer") val mainAnswer: String?
) {
    companion object {
        fun of(question: Question): QuestionRedisDto {
            return QuestionRedisDto(
                question.number,
                question.description,
                question.type,
                question.sourceType,
                question.source,
                question.score,
                question.timeLimit,
                question.getMainAnswerContent()
            )
        }
    }
}