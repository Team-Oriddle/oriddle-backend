package com.tuk.oriddle.domain.quiz.controller.doc

import com.tuk.oriddle.domain.quiz.dto.response.QuizInfoResponse
import com.tuk.oriddle.domain.quiz.dto.response.QuizListResponse
import com.tuk.oriddle.global.result.ResultResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "Quiz", description = "퀴즈 관련 API")
interface QuizApiDoc {
    @Operation(summary = "퀴즈 정보 조회")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = QuizInfoResponse::class)
            )]
        )]
    )
    fun getQuizInfo(@PathVariable quizId: Long): ResponseEntity<ResultResponse>

    @Operation(summary = "퀴즈 목록 조회")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = QuizListResponse::class)
            )]
        )]
    )
    fun getQuizzes(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int,
    ): ResponseEntity<ResultResponse>
}
