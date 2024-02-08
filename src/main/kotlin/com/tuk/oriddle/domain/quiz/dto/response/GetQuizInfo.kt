package com.tuk.oriddle.domain.quiz.dto.response

import com.tuk.oriddle.domain.question.entity.QuestionSourceType
import com.tuk.oriddle.domain.question.entity.QuestionType
import com.tuk.oriddle.domain.quiz.entity.Quiz
import java.util.stream.Collectors


data class GetQuizInfo(
    val quizId: Long,
    val title: String,
    val makerName: String,
    val imageUrl: String,
    val description: String,
    val questionSourceTypes: List<String>,
    val answerTypes: List<String>
)

fun toGetQuizInfo(
    quiz: Quiz,
    questionSourceTypes: List<QuestionSourceType>,
    questionTypes: List<QuestionType>
): GetQuizInfo {
    return GetQuizInfo(
        quizId = quiz.id,
        title = quiz.title,
        makerName = quiz.maker.nickname,
        imageUrl = quiz.image,
        description = quiz.description,
        questionSourceTypes = questionSourceTypes
            .stream()
            .map { it.name }
            .collect(Collectors.toList()),
        answerTypes = questionTypes.stream()
            .map { it.name }
            .collect(Collectors.toList())
    )
}