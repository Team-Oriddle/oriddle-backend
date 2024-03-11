package com.tuk.oriddle.domain.quizroom.repository

import com.tuk.oriddle.domain.quizroom.entity.QuizRoomProgressStatus
import org.springframework.data.repository.CrudRepository

interface QuizRoomStatusRedisRepository : CrudRepository<QuizRoomProgressStatus, Long> {
}