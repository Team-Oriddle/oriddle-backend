package com.tuk.oriddle.domain.quiz.dto.response

import com.tuk.oriddle.domain.question.entity.QuestionSourceType
import com.tuk.oriddle.domain.question.entity.QuestionType
import com.tuk.oriddle.domain.quiz.entity.Quiz

data class QuizInfoResponse(
    val quizId: Long,
    val title: String,
    val makerName: String,
    val imageUrl: String,
    val description: String,
    val questionSourceTypes: List<String>,
    val answerTypes: List<String>
) {
    companion object {
        fun of(
            quiz: Quiz,
            questionSourceTypes: List<QuestionSourceType>,
            questionTypes: List<QuestionType>
        ): QuizInfoResponse {
            return QuizInfoResponse(
                quizId = quiz.id,
                title = quiz.title,
                makerName = quiz.maker.nickname,
                imageUrl = quiz.image,
                description = quiz.description,
                questionSourceTypes = questionSourceTypes.map { it.name },
                answerTypes = questionTypes.map { it.name }
            )
        }
    }
}