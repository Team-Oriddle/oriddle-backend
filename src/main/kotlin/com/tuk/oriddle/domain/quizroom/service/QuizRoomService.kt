package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.participant.dto.ParticipantInfoGetResponse
import com.tuk.oriddle.domain.participant.entity.Participant
import com.tuk.oriddle.domain.participant.exception.ParticipantNotHostException
import com.tuk.oriddle.domain.participant.service.ParticipantQueryService
import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.service.QuizQueryService
import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomInfoGetResponse
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomJoinResponse
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomAlreadyParticipantException
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomFullException
import com.tuk.oriddle.domain.quizroom.exception.UserNotInQuizRoomException
import com.tuk.oriddle.domain.user.entity.User
import com.tuk.oriddle.domain.user.service.UserQueryService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// TODO: 서비스 분리 및 리팩토링 필요
@Service
class QuizRoomService(
    private val quizQueryService: QuizQueryService,
    private val userQueryService: UserQueryService,
    private val participantQueryService: ParticipantQueryService,
    private val quizRoomMessageService: QuizRoomMessageService,
    private val quizRoomQueryService: QuizRoomQueryService,
) {
    fun getQuizRoomInfo(quizRoomId: Long): QuizRoomInfoGetResponse {
        val quizRoom: QuizRoom = quizRoomQueryService.findById(quizRoomId)
        val participants: List<Participant> = participantQueryService.findByQuizRoomId(quizRoomId)
        val participantsInfo: List<ParticipantInfoGetResponse> = participants
            .stream().map { ParticipantInfoGetResponse.of(it) }
            .toList()
        return QuizRoomInfoGetResponse.of(quizRoom, participantsInfo)
    }

    @Transactional
    fun createQuizRoom(
        request: QuizRoomCreateRequest, userId: Long
    ): QuizRoomCreateResponse {
        val quiz: Quiz = quizQueryService.findById(request.quizId)
        val quizRoom = request.toEntity(quiz)
        val user: User = userQueryService.findById(userId)
        quizRoomQueryService.save(quizRoom)
        val participant = Participant(quizRoom, user, true)
        participantQueryService.save(participant)
        quizRoomMessageService.sendQuizRoomJoinMessage(
            quizRoom.id,
            user.id,
            user.nickname,
            participant.position
        )
        return QuizRoomCreateResponse.of(quizRoom.id, participant.position)
    }

    @Transactional
    fun joinQuizRoom(quizRoomId: Long, userId: Long): QuizRoomJoinResponse {
        // TODO: 쿼리 최적화 필요
        val quizRoom = quizRoomQueryService.findById(quizRoomId)
        val user: User = userQueryService.findById(userId)
        checkJoinQuizRoom(quizRoom, user)
        val participant = Participant(quizRoom, user, false)
        participantQueryService.save(participant)
        quizRoomMessageService.sendQuizRoomJoinMessage(
            quizRoomId,
            user.id,
            user.nickname,
            participant.position
        )
        return QuizRoomJoinResponse.of(quizRoomId, userId, participant.position)
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