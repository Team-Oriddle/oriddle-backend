package com.tuk.oriddle.domain.quizroom.entity

import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class QuizRoom(
        title: String,
        maxParticipant: Integer,
        questionCount: Integer
) : BaseEntity() {
    @Column(name = "title", nullable = false)
    var title: String = title
        private set

    @Column(name = "max_participant", nullable = false)
    var maxParticipant: Integer = maxParticipant
        private set

    @Column(name = "question_count", nullable = false)
    var questionCount: Integer = questionCount
        private set
}