package com.tuk.oriddle.domain.quizroom.entity

import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
class QuizRoom(
    title: String,
    maxParticipant: Integer,
    questionCount: Integer,
    quiz: Quiz
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    var quiz: Quiz = quiz
        private set
}