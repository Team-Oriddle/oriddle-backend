package com.tuk.oriddle.domain.quizroom.controller.doc

import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomInfoGetResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomJoinResponse
import com.tuk.oriddle.global.annotation.LoginUser
import com.tuk.oriddle.global.result.ResultResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "QuizRoom", description = "퀴즈방 관련 API")
interface QuizRoomApiDoc {
    @Operation(summary = "퀴즈방 정보 조회", security = [SecurityRequirement(name = "ROLE_USER")])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = QuizRoomInfoGetResponse::class)
            )]
        )]
    )
    fun getQuizRoomInfo(@PathVariable(name = "room-id") roomId: Long): ResponseEntity<ResultResponse>

    @Operation(summary = "퀴즈방 생성", security = [SecurityRequirement(name = "ROLE_USER")])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = QuizRoomCreateResponse::class)
            )]
        )]
    )
    fun createQuizRoom(
        @Valid @RequestBody request: QuizRoomCreateRequest, @LoginUser userId: Long
    ): ResponseEntity<ResultResponse>

    @Operation(summary = "퀴즈방 참가", security = [SecurityRequirement(name = "ROLE_USER")])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = QuizRoomJoinResponse::class)
            )]
        )]
    )
    fun joinQuizRoom(
        @PathVariable(name = "room-id") roomId: Long, @LoginUser userId: Long
    ): ResponseEntity<ResultResponse>

    @Operation(summary = "퀴즈방 퇴장", security = [SecurityRequirement(name = "ROLE_USER")])
    fun leaveQuizRoom(
        @PathVariable(name = "room-id") roomId: Long, @LoginUser userId: Long
    ): ResponseEntity<ResultResponse>

    @Operation(summary = "퀴즈방 시작", security = [SecurityRequirement(name = "ROLE_USER")])
    fun startQuizRoom(
        @PathVariable(name = "room-id") roomId: Long, @LoginUser userId: Long
    ): ResponseEntity<ResultResponse>
}