package com.tuk.oriddle.domain.quizroom.controller

import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.service.QuizRoomService
import com.tuk.oriddle.global.result.ResultCode.QUIZ_ROOM_CREATE_SUCCESS
import com.tuk.oriddle.global.result.ResultResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/quiz-room")
class QuizRoomController(private val quizRoomService: QuizRoomService) {
    @PostMapping
    fun createQuizRoom(
        @Valid @RequestBody request: QuizRoomCreateRequest
    ): ResponseEntity<ResultResponse> {
        val quizRoom: QuizRoomCreateResponse = quizRoomService.createQuizRoom(request)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_CREATE_SUCCESS, quizRoom))
    }
}