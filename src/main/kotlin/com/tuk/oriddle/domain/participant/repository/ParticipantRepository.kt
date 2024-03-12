package com.tuk.oriddle.domain.participant.repository

import com.tuk.oriddle.domain.participant.entity.Participant
import org.springframework.data.jpa.repository.JpaRepository

interface ParticipantRepository : JpaRepository<Participant, Long> {
    fun existsByQuizRoomIdAndUserId(quizRoomId: Long, userId: Long): Boolean
    fun deleteByQuizRoomIdAndUserId(quizRoomId: Long, userId: Long)
    fun findByQuizRoomId(quizRoomId: Long): List<Participant>
    fun findByQuizRoomIdAndUserId(quizRoomId: Long, userId: Long): Participant
}