package com.tuk.oriddle.domain.quizroom.entity

import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import lombok.*

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
internal class QuizRoom : BaseEntity() {

    @Column(name = "title", nullable = false)
    private lateinit var title: String

    @Column(name = "max_participant", nullable = false)
    private lateinit var maxParticipant: Integer

    @Column(name = "question_count", nullable = false)
    private lateinit var questionCount: Integer
}