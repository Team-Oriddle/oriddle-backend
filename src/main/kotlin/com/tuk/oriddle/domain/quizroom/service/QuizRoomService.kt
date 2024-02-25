package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.participant.entity.Participant
import com.tuk.oriddle.domain.participant.service.ParticipantQueryService
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.service.QuizQueryService
import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomJoinResponse
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomFullException
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomNotFoundException
import com.tuk.oriddle.domain.quizroom.repository.QuizRoomRepository
import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.domain.user.service.UserQueryService
import org.springframework.stereotype.Service

@Service
class QuizRoomService(
    private val quizRoomRepository: QuizRoomRepository,
    private val quizQueryService: QuizQueryService,
    private val userQueryService: UserQueryService,
    private val participantQueryService: ParticipantQueryService
) {
    fun createQuizRoom(quizRoomCreateRequest: QuizRoomCreateRequest, userId: Long): QuizRoomCreateResponse {
        val quiz: Quiz = quizQueryService.findById(quizRoomCreateRequest.quizId)
        val quizRoom: QuizRoom = QuizRoomCreateRequest.of(quiz, quizRoomCreateRequest)
        val user: User = userQueryService.findById(userId)
        checkQuizRoomJoinable(quizRoom) // 참가할 수 있는 상태인지 검증
        val participant = Participant(quizRoom, user)
        quizRoomRepository.save(quizRoom)
        participantQueryService.save(participant)
        return QuizRoomCreateResponse.of(quizRoom.id)
    }

    fun joinQuizRoom(quizRoomId: Long, userId: Long): QuizRoomJoinResponse {
        val quizRoom: QuizRoom = quizRoomRepository.findById(quizRoomId)
            .orElseThrow{ QuizRoomNotFoundException() }
        val user: User = userQueryService.findById(userId)
        checkQuizRoomJoinable(quizRoom) // 참가할 수 있는 상태인지 검증
        val participant = Participant(quizRoom, user)
        participantQueryService.save(participant)
        return QuizRoomJoinResponse.of(quizRoomId, userId)
    }

    private fun checkQuizRoomJoinable(quizRoom: QuizRoom) {
        checkQuizRoomFull(quizRoom)
    }

    private fun checkQuizRoomFull(quizRoom: QuizRoom) {
        val quizRoomMaxParticipants: Int = quizRoom.maxParticipant.toInt()
        val participantsCount: Int = participantQueryService.countParticipantsByQuizRoomId(quizRoom.id)
        if (quizRoomMaxParticipants - participantsCount <= 0)
            throw QuizRoomFullException()
    }
}