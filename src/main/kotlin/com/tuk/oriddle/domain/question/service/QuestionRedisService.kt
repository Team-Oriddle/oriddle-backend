package com.tuk.oriddle.domain.question.service

import com.tuk.oriddle.domain.question.dto.redis.QuestionRedisDto
import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.question.exception.QuestionNotFoundInRedisException
import com.tuk.oriddle.domain.question.repository.QuestionRedisRepository
import org.springframework.stereotype.Service

@Service
class QuestionRedisService(private val questionRedisRepository: QuestionRedisRepository) {
    fun saveQuestion(quizRoomId: Long, number: Long, question: Question) {
        val key = makeKey(quizRoomId, number)
        val questionRedisDto = QuestionRedisDto.of(question, key)
        questionRedisRepository.save(questionRedisDto)
    }

    fun getQuestion(quizRoomId: Long, number: Long): QuestionRedisDto {
        val key = makeKey(quizRoomId, number)
        return questionRedisRepository.findById(key)
            .orElseThrow { QuestionNotFoundInRedisException() }
    }

    private fun makeKey(quizRoomId: Long, number: Long): String {
        return "$quizRoomId:$number"
    }
}