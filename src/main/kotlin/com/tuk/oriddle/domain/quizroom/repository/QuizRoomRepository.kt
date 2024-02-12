package com.tuk.oriddle.domain.quizroom.repository

import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import org.springframework.data.jpa.repository.JpaRepository

interface QuizRoomRepository : JpaRepository<QuizRoom, Long> {
}