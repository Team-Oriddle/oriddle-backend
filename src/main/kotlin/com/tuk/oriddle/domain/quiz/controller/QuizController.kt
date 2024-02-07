package com.tuk.oriddle.domain.quiz.controller

import com.tuk.oriddle.domain.quiz.dto.QuizCreateRequest
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.service.QuizService
import com.tuk.oriddle.global.result.ResultCode
import com.tuk.oriddle.global.result.ResultResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/quiz")
class QuizController(private val quizService: QuizService) {
    // TODO: 추후 수정 필요 (예시용 API로 사용하기 위해 임시로 구현)
    @PostMapping
    fun createQuiz(
        @RequestBody @Valid quizCreateRequest: QuizCreateRequest
    ): ResponseEntity<ResultResponse> {
        val createdQuiz: Quiz = quizService.createQuiz(quizCreateRequest)
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUIZ_CREATE_SUCCESS, createdQuiz))
    }
}