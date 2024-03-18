package com.tuk.oriddle.domain.user.controller.doc

import com.tuk.oriddle.domain.user.dto.request.UserNicknameUpdateRequest
import com.tuk.oriddle.domain.user.dto.response.UserInfoResponse
import com.tuk.oriddle.domain.user.dto.response.UserNicknameUpdateResponse
import com.tuk.oriddle.global.annotation.LoginUser
import com.tuk.oriddle.global.result.ResultResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "User", description = "사용자 관련 API")
interface UserApiDoc {
    @Operation(summary = "로그인 유저 정보 조회", security = [SecurityRequirement(name = "ROLE_USER")])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserInfoResponse::class)
                )]
            )
        ]
    )
    fun getLoginUserInfo(@LoginUser userId: Long): ResponseEntity<ResultResponse>

    @Operation(summary = "유저 닉네임 수정", security = [SecurityRequirement(name = "ROLE_USER")])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserNicknameUpdateResponse::class)
                )]
            )
        ]
    )
    fun updateUserNickname(
        @LoginUser userId: Long,
        @RequestBody request: UserNicknameUpdateRequest
    ): ResponseEntity<ResultResponse>
}