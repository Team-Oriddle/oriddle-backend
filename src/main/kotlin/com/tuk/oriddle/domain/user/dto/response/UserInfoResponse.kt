package com.tuk.oriddle.domain.user.dto.response

import com.tuk.oriddle.domain.user.entity.User

data class UserInfoResponse(
    val userId: Long, val email: String, val nickname: String
) {
    companion object {
        fun of(
            user: User
        ): UserInfoResponse {
            return UserInfoResponse(
                user.id, user.email, user.nickname
            )
        }
    }
}
