package com.tuk.oriddle.domain.participant.service

import com.tuk.oriddle.domain.participant.dto.redis.ParticipantRedisDto
import com.tuk.oriddle.domain.participant.dto.redis.ParticipantsRedisDto
import com.tuk.oriddle.domain.participant.entity.Participant
import com.tuk.oriddle.domain.participant.repository.ParticipantRedisRepository
import org.springframework.stereotype.Service

@Service
class ParticipantRedisService(private val participantRedisRepository: ParticipantRedisRepository) {
    fun saveParticipants(quizRoomId: Long, participants: MutableList<Participant>) {
        val participantInfos = makeParticipantInfosByParticipants(participants)
        participantRedisRepository.save(ParticipantsRedisDto(quizRoomId, participantInfos))
    }

    private fun makeParticipantInfosByParticipants(participants: MutableList<Participant>): MutableList<ParticipantRedisDto> {
        return participants.map {
            ParticipantRedisDto(it.user.id, it.position)
        } as MutableList<ParticipantRedisDto>
    }
}