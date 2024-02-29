package com.tuk.oriddle.domain.quizroom.controller

import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomJoinResponse
import com.tuk.oriddle.domain.quizroom.service.QuizRoomService
import com.tuk.oriddle.global.result.ResultCode.QUIZ_ROOM_CREATE_SUCCESS
import com.tuk.oriddle.global.result.ResultCode.QUIZ_ROOM_JOIN_SUCCESS
import com.tuk.oriddle.global.result.ResultResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/quiz-room")
class QuizRoomController(private val quizRoomService: QuizRoomService) {
    @Secured("ROLE_USER")
    @PostMapping
    fun createQuizRoom(
        @Valid @RequestBody request: QuizRoomCreateRequest,
        @AuthenticationPrincipal oauth2User: OAuth2User
    ): ResponseEntity<ResultResponse> {
        val userId = oauth2User.name.toLong()
        val quizRoom: QuizRoomCreateResponse = quizRoomService.createQuizRoom(request, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_CREATE_SUCCESS, quizRoom))
    }

    @PostMapping("/{room-id}/join")
    fun joinQuizRoom(
        @PathVariable(name = "room-id")
        roomId: Long,
        @RequestParam(name = "user-id")
        userId: Long
    ): ResponseEntity<ResultResponse> {
        val quizRoomJoin: QuizRoomJoinResponse = quizRoomService.joinQuizRoom(roomId, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_JOIN_SUCCESS, quizRoomJoin))
    }
}