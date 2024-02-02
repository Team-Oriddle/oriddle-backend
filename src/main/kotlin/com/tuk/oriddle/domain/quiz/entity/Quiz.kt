package com.tuk.oriddle.domain.quiz.entity

import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Quiz(
    title: String,
    description: String,
    image: String,
    maker: User
) : BaseEntity() {
    @Column(nullable = false, length = 100)
    var title: String = title
        private set

    @Column(nullable = false, length = 200)
    var description: String = description
        private set

    @Column(nullable = false, length = 200)
    var image: String = image
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id", nullable = false)
    var maker: User = maker
        private set
}