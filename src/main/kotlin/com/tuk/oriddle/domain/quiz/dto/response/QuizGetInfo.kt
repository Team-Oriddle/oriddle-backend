package com.tuk.oriddle.domain.quiz.dto.response

import com.tuk.oriddle.domain.question.entity.AnswerType
import com.tuk.oriddle.domain.question.entity.QuestionSourceType
import com.tuk.oriddle.domain.quiz.entity.Quiz
import java.util.stream.Collectors


data class QuizGetInfo(
        val quizId: Long,
        val title: String,
        val makerName: String,
        val imageUrl: String,
        val description: String,
        val questionSourceTypes: List<String>,
        val answerTypes: List<String>
)

fun toQuizGetInfo(quiz: Quiz, questionSourceTypes: List<QuestionSourceType>, answerTypes: List<AnswerType>): QuizGetInfo {
    return QuizGetInfo(
            quizId = quiz.id,
            title = quiz.title,
            makerName = quiz.maker.nickname,
            imageUrl = quiz.image,
            description = quiz.description,
            questionSourceTypes = questionSourceTypes
                    .stream()
                    .map{it.name}
                    .collect(Collectors.toList()),
            answerTypes = answerTypes.stream()
                    .map{it.name}
                    .collect(Collectors.toList())
    )
}