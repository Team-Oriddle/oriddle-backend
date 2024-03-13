package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomNotFoundException
import com.tuk.oriddle.domain.quizroom.repository.QuizRoomRepository
import org.springframework.stereotype.Service

@Service
class QuizRoomQueryService(private val quizRoomRepository: QuizRoomRepository) {
    fun findById(quizRoomId: Long): QuizRoom {
        return quizRoomRepository.findById(quizRoomId).orElseThrow { QuizRoomNotFoundException() }
    }

    fun save(quizRoom: QuizRoom) {
        quizRoomRepository.save(quizRoom)
    }
}