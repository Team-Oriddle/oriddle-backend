package com.tuk.oriddle.domain.user.dto.response

import com.tuk.oriddle.domain.user.entity.User
import java.time.LocalDateTime

data class UserNicknameUpdateResponse(
    val nickname: String,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun of(
            user: User
        ): UserNicknameUpdateResponse {
            return UserNicknameUpdateResponse(
                nickname = user.nickname,
                updatedAt = user.updatedAt
            )
        }
    }
}
