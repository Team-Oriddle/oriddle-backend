package com.tuk.oriddle.domain.participant.entity

import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Participant(
       quizRoom: QuizRoom,
       user: User
) : BaseEntity() {
    @ManyToOne
    @JoinColumn(name = "quiz_room_id", nullable = false)
    var quizRoom: QuizRoom = quizRoom
        private set

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = user
        private set
}
