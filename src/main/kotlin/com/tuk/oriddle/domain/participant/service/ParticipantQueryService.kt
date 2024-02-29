package com.tuk.oriddle.domain.participant.service

import com.tuk.oriddle.domain.participant.entity.Participant
import com.tuk.oriddle.domain.participant.repository.ParticipantRepository
import org.springframework.stereotype.Service

@Service
class ParticipantQueryService(private val participantRepository: ParticipantRepository) {
    fun save(participant: Participant) {
        participantRepository.save(participant)
    }

    fun isUserAlreadyParticipant(quizRoomId: Long, userId: Long): Boolean {
        return participantRepository.existsByQuizRoomIdAndUserId(quizRoomId, userId)
    }

    fun leaveQuizRoom(quizRoomId: Long, userId: Long) {
        participantRepository.deleteByQuizRoomIdAndUserId(quizRoomId, userId)
    }
}