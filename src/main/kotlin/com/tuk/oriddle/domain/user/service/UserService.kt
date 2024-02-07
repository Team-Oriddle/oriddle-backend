package com.tuk.oriddle.domain.user.service

import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.domain.user.exception.UserNotFoundException
import com.tuk.oriddle.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUser(id: Long): User {
        return userRepository.findById(id).orElseThrow { UserNotFoundException() }
    }
}