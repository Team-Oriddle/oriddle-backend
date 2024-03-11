package com.tuk.oriddle.domain.user.service

import com.tuk.oriddle.domain.user.dto.request.UserNicknameUpdateRequest
import com.tuk.oriddle.domain.user.dto.response.UserNicknameUpdateResponse
import com.tuk.oriddle.domain.user.entity.Modifier
import com.tuk.oriddle.domain.user.entity.User
import jakarta.transaction.Transactional
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class UserService(private val userQueryService: UserQueryService) {
    private val randomDigit: Int = 4
    private val characterName: String = "오리"
    fun createByEmail(email: String): User {
        val user = User(email = email, nickname = generateRandomNickname(characterName, randomDigit))
        return userQueryService.save(user)
    }

    @Transactional
    fun updateNickname(oauth2User: OAuth2User, request: UserNicknameUpdateRequest): UserNicknameUpdateResponse {
        val userId = oauth2User.attributes["userId"] as Long
        val user = userQueryService.findById(userId)
        user.updateNickname(request.getUpdatedNickname())
        return UserNicknameUpdateResponse.of(user)
    }

    private fun generateRandomNickname(duck: String, numberLength: Int): String {
        val randomModifier = Modifier.getRandomModifier()
        val randomNumber = (0 until 10.0.pow(numberLength).toInt()).random().toString().padStart(numberLength, '0')
        return "${randomModifier.value}$duck$randomNumber"
    }
}