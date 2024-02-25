package com.tuk.oriddle.domain.user.service

import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.domain.user.exception.UserNotFoundException
import com.tuk.oriddle.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserQueryService(private val userRepository: UserRepository) {
    fun findById(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow { UserNotFoundException() }
    }

    fun findOrCreateUserByEmail(email: String): User {
        // TODO: nickname 자동 생성 로직 추가하기
        return userRepository.findByEmail(email)?.let { it }
            ?: userRepository.save(User(email = email, nickname = "기본 이름"))
    }
}