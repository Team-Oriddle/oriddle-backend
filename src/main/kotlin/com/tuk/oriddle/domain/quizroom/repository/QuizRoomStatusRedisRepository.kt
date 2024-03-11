package com.tuk.oriddle.domain.quizroom.repository

import com.tuk.oriddle.domain.quizroom.dto.redis.QuizRoomStatusRedisDto
import org.springframework.data.repository.CrudRepository

interface QuizRoomStatusRedisRepository : CrudRepository<QuizRoomStatusRedisDto, Long> {
}