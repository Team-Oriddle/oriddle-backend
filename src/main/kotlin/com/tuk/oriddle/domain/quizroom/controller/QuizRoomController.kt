package com.tuk.oriddle.domain.quizroom.controller

import com.tuk.oriddle.domain.quizroom.controller.doc.QuizRoomApiDoc
import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomInfoGetResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomJoinResponse
import com.tuk.oriddle.domain.quizroom.service.QuizRoomProgressService
import com.tuk.oriddle.domain.quizroom.service.QuizRoomService
import com.tuk.oriddle.global.annotation.LoginUser
import com.tuk.oriddle.global.result.ResultCode.*
import com.tuk.oriddle.global.result.ResultResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/quiz-room")
class QuizRoomController(
    private val quizRoomService: QuizRoomService,
    private val quizRoomProgressService: QuizRoomProgressService
) : QuizRoomApiDoc {
    @Secured("ROLE_USER")
    @GetMapping("/{room-id}")
    override fun getQuizRoomInfo(
        @PathVariable(name = "room-id") roomId: Long
    ): ResponseEntity<ResultResponse> {
        val response: QuizRoomInfoGetResponse = quizRoomService.getQuizRoomInfo(roomId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_GET_INFO_SUCCESS, response))
    }

    @Secured("ROLE_USER")
    @PostMapping
    override fun createQuizRoom(
        @Valid @RequestBody request: QuizRoomCreateRequest,
        @LoginUser userId: Long
    ): ResponseEntity<ResultResponse> {
        val response: QuizRoomCreateResponse = quizRoomService.createQuizRoom(request, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_CREATE_SUCCESS, response))
    }

    @Secured("ROLE_USER")
    @PostMapping("/{room-id}/join")
    override fun joinQuizRoom(
        @PathVariable(name = "room-id") roomId: Long,
        @LoginUser userId: Long
    ): ResponseEntity<ResultResponse> {
        val response: QuizRoomJoinResponse = quizRoomService.joinQuizRoom(roomId, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_JOIN_SUCCESS, response))
    }

    @Secured("ROLE_USER")
    @PostMapping("/{room-id}/leave")
    override fun leaveQuizRoom(
        @PathVariable(name = "room-id") roomId: Long,
        @LoginUser userId: Long
    ): ResponseEntity<ResultResponse> {
        quizRoomService.leaveQuizRoom(roomId, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_LEAVE_SUCCESS))
    }

    @Secured("ROLE_USER")
    @PostMapping("/{room-id}/start")
    override fun startQuizRoom(
        @PathVariable(name = "room-id") roomId: Long,
        @LoginUser userId: Long
    ): ResponseEntity<ResultResponse> {
        quizRoomProgressService.startQuizRoom(roomId, userId)
        return ResponseEntity.ok(ResultResponse.of(QUIZ_ROOM_START_SUCCESS))
    }
}