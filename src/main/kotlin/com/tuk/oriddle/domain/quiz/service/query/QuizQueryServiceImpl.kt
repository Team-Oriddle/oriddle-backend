package com.tuk.oriddle.domain.quiz.service.query

import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.entity.AnswerType
import com.tuk.oriddle.domain.question.entity.QuestionSourceType
import com.tuk.oriddle.domain.question.repository.QuestionRepository
import com.tuk.oriddle.domain.quiz.dto.response.QuizGetInfo
import com.tuk.oriddle.domain.quiz.dto.response.toQuizGetInfo
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.exception.QuizNotFoundException
import com.tuk.oriddle.domain.quiz.repository.QuizRepository
import org.springframework.stereotype.Service

@Service
class QuizQueryServiceImpl(private val quizRepository: QuizRepository, private val questionRepository: QuestionRepository) : QuizQueryService {
    override fun getQuizById(quizId: Long): QuizGetInfo {
        val quiz: Quiz = quizRepository.findById(quizId)
                .orElseThrow { QuizNotFoundException() }
        val questions: List<Question> = questionRepository.findByQuizId(quizId)
        val questionSourceTypes: List<QuestionSourceType> = questions
                        .map { it.questionSourceType }
                        .distinct()
        val answerTypes: List<AnswerType> = questions
                .map { it.answerType }
                .distinct()
        return toQuizGetInfo(quiz,questionSourceTypes,answerTypes)
    }
}