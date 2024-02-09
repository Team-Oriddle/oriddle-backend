package com.tuk.oriddle.domain.quiz.controller

import com.tuk.oriddle.domain.quiz.dto.response.QuizInfoResponse
import com.tuk.oriddle.domain.quiz.dto.response.QuizListResponse
import com.tuk.oriddle.domain.quiz.service.QuizService
import com.tuk.oriddle.global.result.ResultCode.QUIZ_GET_INFO_SUCCESS
import com.tuk.oriddle.global.result.ResultCode.QUIZ_PAGING_GET_SUCCESS
import com.tuk.oriddle.global.result.ResultResponse
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/quiz")
class QuizController(private val quizService: QuizService) {
    @GetMapping("/info/{quizId}")
    fun getQuizInfo(@PathVariable quizId: Long): ResponseEntity<ResultResponse> {
        val quizInfo: QuizInfoResponse = quizService.getQuizById(quizId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_GET_INFO_SUCCESS, quizInfo))
    }

    @GetMapping("/page/{page}")
    fun getQuizzes(
        @PathVariable page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int
    ): ResponseEntity<ResultResponse> {
        val pageReqeust: PageRequest = PageRequest.of(page, pageSize, Sort.by("id").descending());
        val quizzes: QuizListResponse = quizService.getQuizzesWithPaging(pageReqeust)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_PAGING_GET_SUCCESS, quizzes))
    }
}