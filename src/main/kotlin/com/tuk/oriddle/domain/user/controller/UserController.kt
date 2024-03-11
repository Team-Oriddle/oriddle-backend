package com.tuk.oriddle.domain.user.controller

import com.tuk.oriddle.domain.user.dto.request.UserNicknameUpdateRequest
import com.tuk.oriddle.domain.user.dto.response.UserInfoResponse
import com.tuk.oriddle.domain.user.dto.response.UserNicknameUpdateResponse
import com.tuk.oriddle.domain.user.service.UserQueryService
import com.tuk.oriddle.domain.user.service.UserService
import com.tuk.oriddle.global.result.ResultCode
import com.tuk.oriddle.global.result.ResultResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userQueryService: UserQueryService,
    private val userService: UserService) {
    @Secured("ROLE_USER")
    @GetMapping("/info")
    fun getLoginUserInfo(@AuthenticationPrincipal oauth2User: OAuth2User): ResponseEntity<ResultResponse> {
        val userId = oauth2User.attributes["userId"] as Long
        val user = userQueryService.findById(userId)
        val userInfoResponse = UserInfoResponse.of(user)
        return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_GET_SUCCESS, userInfoResponse))
    }

    @Secured("ROLE_USER")
    @PatchMapping("/nickname")
    fun updateUserNickname(
        @AuthenticationPrincipal oauth2User: OAuth2User,
        @RequestBody request: UserNicknameUpdateRequest
    ): ResponseEntity<ResultResponse> {
        val userNicknameUpdateResponse: UserNicknameUpdateResponse = userService.updateNickname(oauth2User, request)
        return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_NICKNAME_UPDATE_SUCCESS, userNicknameUpdateResponse))
    }
}