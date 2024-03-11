package com.tuk.oriddle.domain.user.service

import com.tuk.oriddle.domain.user.entity.Modifier
import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.domain.user.exception.UserNotFoundException
import com.tuk.oriddle.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class UserQueryService(private val userRepository: UserRepository) {
    private val randomDigit: Int = 4
    private val characterName: String = "오리"

    fun findById(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow { UserNotFoundException() }
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun createByEmail(email: String): User {
        return userRepository.save(User(email = email, nickname = generateRandomNickname(characterName, randomDigit)))
    }


    private fun generateRandomNickname(duck: String, numberLength: Int): String {
        val randomModifier = Modifier.getRandomModifier()
        val randomNumber = (0 until 10.0.pow(numberLength).toInt()).random().toString().padStart(numberLength, '0')
        return "${randomModifier.value}$duck$randomNumber"
    }

}