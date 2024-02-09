package com.tuk.oriddle.domain.quiz.dto.response

import com.tuk.oriddle.domain.quiz.entity.Quiz
import org.springframework.data.domain.Page

data class QuizListResponse(
    val quizzes: List<QuizSimpleResponse>
) {
    companion object {
        fun of(page: Page<Quiz>): QuizListResponse {
            return QuizListResponse(
                quizzes = page.content.map { quiz ->
                    QuizSimpleResponse(
                        quizId = quiz.id,
                        title = quiz.title,
                        imageUrl = quiz.image,
                        description = quiz.description
                    )
                }
            )
        }
    }
}

data class QuizSimpleResponse(
    val quizId: Long,
    val title: String,
    val imageUrl: String,
    val description: String
)
