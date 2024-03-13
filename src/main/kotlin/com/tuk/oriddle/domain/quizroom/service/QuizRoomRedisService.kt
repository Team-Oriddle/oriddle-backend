package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.quizroom.entity.QuizRoomProgressStatus
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomStatusNotFoundInRedisException
import com.tuk.oriddle.domain.quizroom.repository.QuizRoomStatusRedisRepository
import org.springframework.stereotype.Service

@Service
class QuizRoomRedisService(
    private val quizRoomStatusRedisRepository: QuizRoomStatusRedisRepository
) {
    fun saveQuizStatus(quizRoomId: Long, questionCount: Long) {
        val status = QuizRoomProgressStatus(quizRoomId, questionCount)
        quizRoomStatusRedisRepository.save(status)
    }

    fun saveQuizStatus(status: QuizRoomProgressStatus) {
        quizRoomStatusRedisRepository.save(status)
    }

    fun getQuizStatus(quizRoomId: Long): QuizRoomProgressStatus {
        return quizRoomStatusRedisRepository.findById(quizRoomId)
            .orElseThrow { QuizRoomStatusNotFoundInRedisException() }
    }
}