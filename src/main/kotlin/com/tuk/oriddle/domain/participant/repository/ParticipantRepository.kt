package com.tuk.oriddle.domain.participant.repository

import com.tuk.oriddle.domain.participant.entity.Participant
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ParticipantRepository : JpaRepository<Participant, Long> {
    fun findByQuizRoomIdAndUserId(quizRoomId: Long, userId: Long): Optional<Participant>
}