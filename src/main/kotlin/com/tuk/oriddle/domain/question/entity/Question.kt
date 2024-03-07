package com.tuk.oriddle.domain.question.entity

import com.tuk.oriddle.domain.answer.entity.Answer
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Question(
    number: Long,
    description: String,
    source: String? = null,
    type: QuestionType,
    sourceType: QuestionSourceType,
    timeLimit: Int,
    score: Int,
    quiz: Quiz
) : BaseEntity() {
    @Column(nullable = false)
    var number: Long = number
        private set

    @Column(nullable = false, length = 200)
    var description: String = description
        private set

    @Column(length = 200)
    var source: String? = source
        private set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    var type: QuestionType = type
        private set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    var sourceType: QuestionSourceType = sourceType
        private set

    @Column(nullable = false)
    var timeLimit: Int = timeLimit
        private set

    @Column(nullable = false)
    var score: Int = score
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    var quiz: Quiz = quiz
        private set

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    var answers: MutableList<Answer> = mutableListOf()
        private set

    fun getMainAnswerContent(): String? {
        for (answer in answers) {
            if (answer.isMainAnswer) return answer.content
        }
        return null
    }
}