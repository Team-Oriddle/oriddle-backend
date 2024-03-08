package com.tuk.oriddle.domain.question.repository

import com.tuk.oriddle.domain.question.entity.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long> {
    fun findByQuizId(quizId: Long): List<Question>
}