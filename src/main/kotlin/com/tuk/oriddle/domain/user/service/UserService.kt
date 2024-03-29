package com.tuk.oriddle.domain.user.service

import com.tuk.oriddle.domain.user.dto.request.UserNicknameUpdateRequest
import com.tuk.oriddle.domain.user.dto.response.UserInfoResponse
import com.tuk.oriddle.domain.user.dto.response.UserNicknameUpdateResponse
import com.tuk.oriddle.domain.user.entity.Modifier
import com.tuk.oriddle.domain.user.entity.User
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class UserService(private val userQueryService: UserQueryService) {
    private val randomDigit: Int = 4
    private val characterName: String = "오리"
    fun getLoginUserInfo(userId: Long): UserInfoResponse {
        val user = userQueryService.findById(userId)
        return UserInfoResponse.of(user)
    }

    fun createByEmail(email: String): User {
        val user =
            User(email = email, nickname = generateRandomNickname(characterName, randomDigit))
        return userQueryService.save(user)
    }

    @Transactional
    fun updateNickname(
        userId: Long, request: UserNicknameUpdateRequest
    ): UserNicknameUpdateResponse {
        val user = userQueryService.findById(userId)
        user.updateNickname(request.getUpdatedNickname())
        return UserNicknameUpdateResponse.of(user)
    }

    // TODO: 닉네임 랜덤 생성 로직 분리
    private fun generateRandomNickname(duck: String, numberLength: Int): String {
        val randomModifier = Modifier.getRandomModifier()
        val randomNumber =
            (0 until 10.0.pow(numberLength).toInt()).random().toString().padStart(numberLength, '0')
        return "${randomModifier.value}$duck$randomNumber"
    }
}