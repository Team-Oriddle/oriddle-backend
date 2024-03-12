package com.tuk.oriddle.domain.quizroom.controller

import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomInfoGetResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomJoinResponse
import com.tuk.oriddle.domain.quizroom.service.QuizRoomService
import com.tuk.oriddle.global.result.ResultCode.*
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
    @GetMapping("/{room-id}")
    fun getQuizRoomInfo(
        @PathVariable(name =  "room-id") roomId: Long
    ): ResponseEntity<ResultResponse> {
        val response: QuizRoomInfoGetResponse = quizRoomService.getQuizRoomInfo(roomId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_GET_INFO_SUCCESS, response))
    }

    @Secured("ROLE_USER")
    @PostMapping
    fun createQuizRoom(
        @Valid @RequestBody request: QuizRoomCreateRequest,
        @AuthenticationPrincipal oauth2User: OAuth2User
    ): ResponseEntity<ResultResponse> {
        val userId = oauth2User.name.toLong()
        val response: QuizRoomCreateResponse = quizRoomService.createQuizRoom(request, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_CREATE_SUCCESS, response))
    }

    @Secured("ROLE_USER")
    @PostMapping("/{room-id}/join")
    fun joinQuizRoom(
        @PathVariable(name = "room-id") roomId: Long,
        @AuthenticationPrincipal oauth2User: OAuth2User
    ): ResponseEntity<ResultResponse> {
        val userId = oauth2User.name.toLong()
        val response: QuizRoomJoinResponse = quizRoomService.joinQuizRoom(roomId, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_JOIN_SUCCESS, response))
    }

    @Secured("ROLE_USER")
    @PostMapping("/{room-id}/leave")
    fun leaveQuizRoom(
        @PathVariable(name = "room-id") roomId: Long,
        @AuthenticationPrincipal oauth2User: OAuth2User
    ): ResponseEntity<ResultResponse> {
        val userId = oauth2User.name.toLong()
        quizRoomService.leaveQuizRoom(roomId, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_LEAVE_SUCCESS))
    }

    @Secured("ROLE_USER")
    @PostMapping("/{room-id}/start")
    fun startQuizRoom(
        @PathVariable(name = "room-id") roomId: Long,
        @AuthenticationPrincipal oauth2User: OAuth2User
    ): ResponseEntity<ResultResponse> {
        // TODO: 방장만 시작할 수 있도록 변경
        val userId = oauth2User.name.toLong()
        quizRoomService.startQuizRoom(roomId, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_START_SUCCESS))
    }
}