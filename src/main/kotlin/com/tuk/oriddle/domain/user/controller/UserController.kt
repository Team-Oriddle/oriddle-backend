package com.tuk.oriddle.domain.user.controller

import com.tuk.oriddle.domain.user.controller.doc.UserApiDoc
import com.tuk.oriddle.domain.user.dto.request.UserNicknameUpdateRequest
import com.tuk.oriddle.domain.user.dto.response.UserInfoResponse
import com.tuk.oriddle.domain.user.dto.response.UserNicknameUpdateResponse
import com.tuk.oriddle.domain.user.service.UserService
import com.tuk.oriddle.global.annotation.LoginUser
import com.tuk.oriddle.global.result.ResultCode
import com.tuk.oriddle.global.result.ResultResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) : UserApiDoc {
    @Secured("ROLE_USER")
    @GetMapping("/info")
    override fun getLoginUserInfo(@LoginUser userId: Long): ResponseEntity<ResultResponse> {
        val userInfoResponse: UserInfoResponse = userService.getLoginUserInfo(userId)
        return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_GET_SUCCESS, userInfoResponse))
    }

    @Secured("ROLE_USER")
    @PatchMapping("/nickname")
    override fun updateUserNickname(
        @LoginUser userId: Long,
        @RequestBody request: UserNicknameUpdateRequest
    ): ResponseEntity<ResultResponse> {
        val userNicknameUpdateResponse: UserNicknameUpdateResponse =
            userService.updateNickname(userId, request)
        return ResponseEntity.ok(
            ResultResponse.of(
                ResultCode.USER_NICKNAME_UPDATE_SUCCESS,
                userNicknameUpdateResponse
            )
        )
    }
}