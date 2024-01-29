package com.tuk.oriddle.domain.quiz.entity

import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Quiz(
    title: String,
    description: String,
    image: String
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
}