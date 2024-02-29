package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.participant.entity.Participant
import com.tuk.oriddle.domain.participant.service.ParticipantQueryService
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.service.QuizQueryService
import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomJoinResponse
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomAlreadyParticipantException
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomFullException
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomNotFoundException
import com.tuk.oriddle.domain.quizroom.exception.UserNotInQuizRoomException
import com.tuk.oriddle.domain.quizroom.repository.QuizRoomRepository
import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.domain.user.service.UserQueryService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QuizRoomService(
    private val quizRoomRepository: QuizRoomRepository,
    private val quizQueryService: QuizQueryService,
    private val userQueryService: UserQueryService,
    private val participantQueryService: ParticipantQueryService,
    private val quizRoomMessageService: QuizRoomMessageService
) {
    @Transactional
    fun createQuizRoom(
        request: QuizRoomCreateRequest, userId: Long
    ): QuizRoomCreateResponse {
        val quiz: Quiz = quizQueryService.findById(request.quizId)
        val quizRoom = QuizRoom(request.title, request.maxParticipant, quiz)
        val user: User = userQueryService.findById(userId)
        quizRoomRepository.save(quizRoom)
        val participant = Participant(quizRoom, user)
        participantQueryService.save(participant)
        quizRoomMessageService.sendQuizRoomJoinMessage(
            quizRoom.id,
            user.id,
            user.nickname,
            participant.position
        )
        return QuizRoomCreateResponse.of(quizRoom.id)
    }

    @Transactional
    fun joinQuizRoom(quizRoomId: Long, userId: Long): QuizRoomJoinResponse {
        // TODO: 쿼리 최적화 필요
        val quizRoom: QuizRoom =
            quizRoomRepository.findById(quizRoomId).orElseThrow { QuizRoomNotFoundException() }
        val user: User = userQueryService.findById(userId)
        checkJoinQuizRoom(quizRoom, user)
        val participant = Participant(quizRoom, user)
        participantQueryService.save(participant)
        quizRoomMessageService.sendQuizRoomJoinMessage(
            quizRoomId,
            user.id,
            user.nickname,
            participant.position
        )
        return QuizRoomJoinResponse.of(quizRoomId, userId)
    }

    @Transactional
    fun leaveQuizRoom(quizRoomId: Long, userId: Long) {
        if (!participantQueryService.isUserAlreadyParticipant(quizRoomId, userId)) {
            throw UserNotInQuizRoomException()
        }
        participantQueryService.leaveQuizRoom(quizRoomId, userId)
        quizRoomMessageService.sendQuizRoomLeaveMessage(quizRoomId, userId)
    }

    private fun checkJoinQuizRoom(quizRoom: QuizRoom, user: User) {
        checkUserAlreadyParticipant(quizRoom.id, user.id)
        checkQuizRoomFull(quizRoom)
    }

    private fun checkUserAlreadyParticipant(quizRoomId: Long, userId: Long) {
        if (participantQueryService.isUserAlreadyParticipant(quizRoomId, userId))
            throw QuizRoomAlreadyParticipantException()
    }

    private fun checkQuizRoomFull(quizRoom: QuizRoom) {
        if (quizRoom.isFull()) throw QuizRoomFullException()
    }
}