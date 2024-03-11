package com.tuk.oriddle.domain.answer.service

import com.tuk.oriddle.domain.answer.dto.redis.AnswerRedisDto
import com.tuk.oriddle.domain.answer.exception.AnswerNotFoundInRedisException
import com.tuk.oriddle.domain.answer.repository.AnswerRedisRepository
import com.tuk.oriddle.domain.quizroom.dto.message.CheckAnswerMessage
import org.springframework.stereotype.Service

@Service
class AnswerRedisService(private val answerRedisRepository: AnswerRedisRepository) {
    fun isAnswerCorrect(
        answerMessage: CheckAnswerMessage,
        quizRoomId: Long,
        number: Long
    ): Boolean {
        val key = makeKey(quizRoomId, number)
        val answerRedisDto = answerRedisRepository.findById(key).orElseThrow {
            AnswerNotFoundInRedisException()
        }
        return answerRedisDto.values.contains(answerMessage.answer)
    }

    fun saveAnswers(quizRoomId: Long, number: Long, answers: MutableSet<String>) {
        val key = makeKey(quizRoomId, number)
        answerRedisRepository.save(AnswerRedisDto(key, answers))
    }

    private fun makeKey(quizRoomId: Long, number: Long): String {
        return "$quizRoomId:$number"
    }
}