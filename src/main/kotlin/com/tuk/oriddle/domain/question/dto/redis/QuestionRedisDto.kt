package com.tuk.oriddle.domain.question.dto.redis

import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.entity.QuestionSourceType
import com.tuk.oriddle.domain.question.entity.QuestionType
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("question")
data class QuestionRedisDto constructor(
    @Id val key: String,
    val number: Long,
    val description: String,
    val type: QuestionType,
    val sourceType: QuestionSourceType,
    val source: String?,
    val score: Int,
    val timeLimit: Int,
    val mainAnswer: String?
) {
    companion object {
        fun of(question: Question, key: String): QuestionRedisDto {
            return QuestionRedisDto(
                key,
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