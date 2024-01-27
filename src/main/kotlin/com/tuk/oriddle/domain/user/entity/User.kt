package com.tuk.oriddle.domain.user.entity

import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.*
import lombok.*



@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
internal class User : BaseEntity() {

    @Column(name = "email", nullable = false)
    private lateinit var email: String

    @Column(name = "password", nullable = false)
    private lateinit var password: String

    @Column(name = "nickname", nullable = false)
    private lateinit var nickname: String
}
