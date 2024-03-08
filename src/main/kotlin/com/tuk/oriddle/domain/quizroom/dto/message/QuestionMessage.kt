package com.tuk.oriddle.domain.quizroom.dto.message

import com.tuk.oriddle.domain.question.entity.QuestionSourceType
import com.tuk.oriddle.domain.question.entity.QuestionType
import com.tuk.oriddle.domain.quizroom.dto.redis.QuestionRedisDto

data class QuestionMessage(
    val number: Long,
    val description: String,
    val type: QuestionType,
    val sourceType: QuestionSourceType,
    val source: String?,
    val score: Int,
    val timeLimit: Int,
) {
    companion object {
        fun of(questionRedisDto: QuestionRedisDto): QuestionMessage {
            return QuestionMessage(
                questionRedisDto.number,
                questionRedisDto.description,
                questionRedisDto.type,
                questionRedisDto.sourceType,
                questionRedisDto.source,
                questionRedisDto.score,
                questionRedisDto.timeLimit
            )
        }
    }
}