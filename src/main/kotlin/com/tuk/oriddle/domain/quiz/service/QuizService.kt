package com.tuk.oriddle.domain.quiz.service

import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.entity.QuestionSourceType
import com.tuk.oriddle.domain.question.entity.QuestionType
import com.tuk.oriddle.domain.question.service.QuestionQueryService
import com.tuk.oriddle.domain.quiz.dto.response.QuizInfoResponse
import com.tuk.oriddle.domain.quiz.dto.response.QuizListResponse
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.exception.QuizNotFoundException
import com.tuk.oriddle.domain.quiz.repository.QuizRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class QuizService(
    private val quizRepository: QuizRepository,
    private val questionQueryService: QuestionQueryService
) {
    fun getQuizById(quizId: Long): QuizInfoResponse {
        val quiz: Quiz = quizRepository.findById(quizId)
            .orElseThrow { QuizNotFoundException() }
        val questions: List<Question> = questionQueryService.findByQuizId(quizId)
        val questionSourceTypes: List<QuestionSourceType> = questions
            .map { it.sourceType }
            .distinct()
        val questionTypes: List<QuestionType> = questions
            .map { it.type }
            .distinct()
        return QuizInfoResponse.of(quiz, questionSourceTypes, questionTypes)
    }

    fun getQuizzesWithPaging(pageReqeust: PageRequest): QuizListResponse {
        val quizPages: Page<Quiz> = quizRepository.findAll(pageReqeust)
        return QuizListResponse.of(quizPages)
    }
}