package com.tuk.oriddle.domain.quiz.dto.response

import com.tuk.oriddle.domain.quiz.entity.Quiz
import org.springframework.data.domain.Page

data class GetQuiz(
        val quizId: Long,
        val title: String,
        val imageUrl: String,
        val description: String
)

fun toGetQuizzesWithPaging(page: Page<Quiz>): List<GetQuiz> {
    return page.content.map { quiz ->
        GetQuiz(
                quizId = quiz.id,
                title = quiz.title,
                imageUrl = quiz.image,
                description = quiz.description
        )
    }
}