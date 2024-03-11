package com.tuk.oriddle.domain.user.service

import com.tuk.oriddle.domain.user.entity.Modifier
import com.tuk.oriddle.domain.user.entity.User
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

    private fun generateRandomNickname(duck: String, numberLength: Int): String {
        val randomModifier = Modifier.getRandomModifier()
        val randomNumber = (0 until 10.0.pow(numberLength).toInt()).random().toString().padStart(numberLength, '0')
        return "${randomModifier.value}$duck$randomNumber"
    }
}