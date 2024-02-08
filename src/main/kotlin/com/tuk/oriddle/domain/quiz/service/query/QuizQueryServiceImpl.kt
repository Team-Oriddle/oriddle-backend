package com.tuk.oriddle.domain.quiz.service.query

import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.entity.QuestionType
import com.tuk.oriddle.domain.question.entity.QuestionSourceType
import com.tuk.oriddle.domain.question.repository.QuestionRepository
import com.tuk.oriddle.domain.quiz.dto.response.GetQuiz
import com.tuk.oriddle.domain.quiz.dto.response.GetQuizInfo
import com.tuk.oriddle.domain.quiz.dto.response.toGetQuizInfo
import com.tuk.oriddle.domain.quiz.dto.response.toGetQuizzesWithPaging
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.exception.QuizNotFoundException
import com.tuk.oriddle.domain.quiz.repository.QuizRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class QuizQueryServiceImpl(private val quizRepository: QuizRepository, private val questionRepository: QuestionRepository) : QuizQueryService {
    override fun getQuizById(quizId: Long): GetQuizInfo {
        val quiz: Quiz = quizRepository.findById(quizId)
                .orElseThrow { QuizNotFoundException() }
        val questions: List<Question> = questionRepository.findByQuizId(quizId)
        val questionSourceTypes: List<QuestionSourceType> = questions
                        .map { it.sourceType }
                        .distinct()
        val questionTypes: List<QuestionType> = questions
                .map { it.type }
                .distinct()
        return toGetQuizInfo(quiz,questionSourceTypes,questionTypes)
    }

    override fun getQuizzesWithPaging(pageReqeust: PageRequest): List<GetQuiz> {
        val quizPages: Page<Quiz> = quizRepository.findAll(pageReqeust)
        return toGetQuizzesWithPaging(quizPages)
    }
}