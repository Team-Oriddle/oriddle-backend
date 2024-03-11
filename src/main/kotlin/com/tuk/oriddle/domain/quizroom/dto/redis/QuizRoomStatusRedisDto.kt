package com.tuk.oriddle.domain.quizroom.dto.redis

import com.tuk.oriddle.domain.quizroom.entity.QuizProgressStatus
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("quizStatus")
data class QuizRoomStatusRedisDto (
    @Id val quizRoomId: Long,
    val questionCount: Long,
    val status: QuizProgressStatus = QuizProgressStatus.WAIT,
    val currentQuestionNumber: Long = 1
)
