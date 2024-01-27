package com.tuk.oriddle.domain.participant.entity

import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.global.entity.BaseEntity
import jakarta.persistence.*
import lombok.*

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
class Participant : BaseEntity() {

    @ManyToOne
    @JoinColumn(name = "quiz_room_id", nullable = false)
    private lateinit var quizRoom: QuizRoom

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private lateinit var user: User

}
