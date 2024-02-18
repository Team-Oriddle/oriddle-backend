package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quiz.service.QuizQueryService
import com.tuk.oriddle.domain.quizroom.dto.request.QuizRoomCreateRequest
import com.tuk.oriddle.domain.quizroom.dto.response.QuizRoomCreateResponse
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import com.tuk.oriddle.domain.quizroom.repository.QuizRoomRepository
import org.springframework.stereotype.Service

@Service
class QuizRoomService(
    private val quizRoomRepository: QuizRoomRepository,
    private val quizQueryService: QuizQueryService
) {
    fun createQuizRoom(quizRoomCreateRequest: QuizRoomCreateRequest): QuizRoomCreateResponse {
        val quiz: Quiz = quizQueryService.findById(quizRoomCreateRequest.quizId)
        val quizRoom: QuizRoom = QuizRoomCreateRequest.of(quiz, quizRoomCreateRequest)
        quizRoomRepository.save(quizRoom)
        return QuizRoomCreateResponse.of(quizRoom.id)
    }
}