package com.tuk.oriddle.domain.quiz.service.query

import com.tuk.oriddle.domain.quiz.dto.response.GetQuiz
import com.tuk.oriddle.domain.quiz.dto.response.GetQuizInfo
import org.springframework.data.domain.PageRequest

interface QuizQueryService
{
    fun getQuizById(quizId: Long): GetQuizInfo
    fun getQuizzesWithPaging(pageReqeust: PageRequest): List<GetQuiz>
}