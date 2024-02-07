package com.tuk.oriddle.domain.quiz.service.query

import com.tuk.oriddle.domain.quiz.dto.response.QuizGetInfo

interface QuizQueryService
{
    fun getQuizById(quizId: Long): QuizGetInfo
}