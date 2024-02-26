package com.tuk.oriddle.domain.user.repository

import com.tuk.oriddle.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}