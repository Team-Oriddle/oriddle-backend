package com.tuk.oriddle.domain.user.dto.request

import jakarta.validation.constraints.NotBlank

data class UserNicknameUpdateRequest(
    @NotBlank
    private val updatedNickname: String
) {
    fun getUpdatedNickname(): String {
        return updatedNickname
    }
}
