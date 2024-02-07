package com.tuk.oriddle.domain.quiz.repository

import com.tuk.oriddle.domain.quiz.entity.Quiz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizRepository : JpaRepository<Quiz, Long>