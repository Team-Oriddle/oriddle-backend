package com.tuk.oriddle.domain.quiz.service

import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.exception.QuizNotFoundException
import com.tuk.oriddle.domain.quiz.repository.QuizRepository
import org.springframework.stereotype.Service

@Service
class QuizQueryService(private val quizRepository: QuizRepository) {
    fun findById(quizId: Long): Quiz {
        return quizRepository.findById(quizId).orElseThrow { QuizNotFoundException() }
    }
}