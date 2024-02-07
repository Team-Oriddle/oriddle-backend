package com.tuk.oriddle.domain.quiz.controller

import com.tuk.oriddle.domain.quiz.dto.response.QuizGetInfo
import com.tuk.oriddle.domain.quiz.service.query.QuizQueryService
import com.tuk.oriddle.global.result.ResultCode.QUIZ_GET_INFO_SUCCESS
import com.tuk.oriddle.global.result.ResultResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/quiz")
class QuizController(private val quizQueryService: QuizQueryService) {


    @GetMapping("/info/{quizId}")
    fun getQuizInfo(@PathVariable quizId: Long): ResponseEntity<ResultResponse> {
        val quizInfo: QuizGetInfo = quizQueryService.getQuizById(quizId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_GET_INFO_SUCCESS, quizInfo))
    }

}