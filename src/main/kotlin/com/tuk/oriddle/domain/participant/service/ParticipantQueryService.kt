package com.tuk.oriddle.domain.participant.service

import com.tuk.oriddle.domain.participant.entity.Participant
import com.tuk.oriddle.domain.participant.exception.ParticipantNotFoundException
import com.tuk.oriddle.domain.participant.repository.ParticipantRepository
import org.springframework.stereotype.Service

@Service
class ParticipantQueryService(private val participantRepository: ParticipantRepository) {
    fun findByQuizRoomIdAndUserId(quizRoomId: Long, userId: Long): Participant {
        return participantRepository.findByQuizRoomIdAndUserId(quizRoomId, userId)
            .orElseThrow{ ParticipantNotFoundException() }
    }

    fun save(participant: Participant) {
        participantRepository.save(participant)
    }
}