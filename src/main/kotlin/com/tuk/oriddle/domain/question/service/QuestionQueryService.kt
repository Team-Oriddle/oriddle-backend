package com.tuk.oriddle.domain.question.service

import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionQueryService(private val questionRepository: QuestionRepository) {
    fun findByQuizId(quizId: Long): List<Question> {
        return questionRepository.findByQuizId(quizId)
    }
}