package com.tuk.oriddle.domain.quiz.service

import com.tuk.oriddle.domain.quiz.dto.QuizCreateRequest
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.exception.QuizNotFoundException
import com.tuk.oriddle.domain.quiz.repository.QuizRepository
import com.tuk.oriddle.domain.user.service.UserService
import org.springframework.stereotype.Service

@Service
class QuizService(
    private val quizRepository: QuizRepository,
    private val userService: UserService
) {
    fun createQuiz(quizCreateRequest: QuizCreateRequest): Quiz {
        val maker = userService.getUser(quizCreateRequest.makerId)
        val createdQuiz = Quiz(
            title = quizCreateRequest.title,
            description = quizCreateRequest.description,
            image = quizCreateRequest.imageUrl,
            maker = maker
        )
        return quizRepository.save(createdQuiz)
    }
}