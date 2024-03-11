package com.tuk.oriddle.domain.quizroom.service

import com.tuk.oriddle.domain.quizroom.dto.redis.QuizRoomStatusRedisDto
import com.tuk.oriddle.domain.quizroom.exception.QuizRoomStatusNotFoundInRedisException
import com.tuk.oriddle.domain.quizroom.repository.QuizRoomStatusRedisRepository
import org.springframework.stereotype.Service

@Service
class QuizRoomRedisService(
    private val quizRoomStatusRedisRepository: QuizRoomStatusRedisRepository
) {
    fun saveQuizStatus(quizRoomId: Long, questionCount: Long) {
        val status = QuizRoomStatusRedisDto(quizRoomId, questionCount)
        quizRoomStatusRedisRepository.save(status)
    }

    fun getQuizStatus(quizRoomId: Long): QuizRoomStatusRedisDto {
        return quizRoomStatusRedisRepository.findById(quizRoomId)
            .orElseThrow { QuizRoomStatusNotFoundInRedisException() }
    }
}