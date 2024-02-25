package com.tuk.oriddle.domain.user.entity

import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class User(
    email: String,
    nickname: String
) : BaseEntity() {
    @Column(name = "email", nullable = false)
    var email: String = email
        private set

    @Column(name = "nickname", nullable = false)
    var nickname: String = nickname
        private set
}
