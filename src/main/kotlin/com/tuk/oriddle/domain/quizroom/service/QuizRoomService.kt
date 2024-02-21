package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.participant.entity.Participant
import com.tuk.oriddle.domain.participant.exception.AlreadyParticipatingException
import com.tuk.oriddle.domain.participant.service.ParticipantQueryService
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.service.QuizQueryService
import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomJoinResponse
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
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
    fun createQuizRoom(quizRoomCreateRequest: QuizRoomCreateRequest): QuizRoomCreateResponse {
        val quiz: Quiz = quizQueryService.findById(quizRoomCreateRequest.quizId)
        val quizRoom: QuizRoom = QuizRoomCreateRequest.of(quiz, quizRoomCreateRequest)
        quizRoomRepository.save(quizRoom)
        return QuizRoomCreateResponse.of(quizRoom.id)
    }

    fun joinQuizRoom(quizRoomId: Long, userId: Long): QuizRoomJoinResponse {
        val quizRoom: QuizRoom = quizRoomRepository.findById(quizRoomId)
            .orElseThrow{ QuizRoomNotFoundException() }
        val user: User = userQueryService.findById(userId)
//        checkQuizRoomJoinable(quizRoomId, userId) // 참가할 수 있는 상태인지 검증
        val participant = Participant(quizRoom, user)
        participantQueryService.save(participant)
        return QuizRoomJoinResponse.of(quizRoomId, userId)
    }

//    private fun checkQuizRoomJoinable(quizRoomId: Long, userId: Long) {
//        checkUserAlreadyParticipating(quizRoomId, userId)
//    }

//    private fun checkUserAlreadyParticipating(quizRoomId: Long, userId: Long) {
//        val participant: Participant? = participantQueryService.findByQuizRoomIdAndUserId(quizRoomId, userId)
//        if (participant != null)
//            throw AlreadyParticipatingException()
//    }
}