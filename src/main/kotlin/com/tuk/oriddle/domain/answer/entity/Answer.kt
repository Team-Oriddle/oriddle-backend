package com.tuk.oriddle.domain.answer.entity

import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.*
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.LocalDateTime

@Entity
class Answer(
    content: String,
    isMainAnswer: Boolean,
    question: Question
) : BaseEntity() {
    @Column(nullable = false, length = 100)
    var content: String = content
        private set

    @Column(nullable = false)
    var isMainAnswer: Boolean = isMainAnswer
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    var question: Question = question
        private set
}