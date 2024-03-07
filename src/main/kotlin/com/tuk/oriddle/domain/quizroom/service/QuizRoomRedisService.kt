package com.tuk.oriddle.domain.quizroom.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.tuk.oriddle.domain.participant.entity.Participant
import com.tuk.oriddle.domain.question.entity.Question
import com.tuk.oriddle.domain.quizroom.dto.redis.QuestionRedisDto
import com.tuk.oriddle.domain.quizroom.dto.redis.QuizStatusRedisDto
import com.tuk.oriddle.domain.quizroom.dto.redis.UserRedisDto
import com.tuk.oriddle.domain.quizroom.exception.QuizStatusNotFoundInRedisException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

// TODO: Redis Repository 계층을 쓰도록 리팩토링
@Service
class QuizRoomRedisService(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val objectMapper: ObjectMapper
) {
    fun saveQuizStatus(quizRoomId: Long, quizId: Long, questionCount: Long) {
        val key = "qr:$quizRoomId:status"
        val status = QuizStatusRedisDto(quizId, questionCount)
        redisTemplate.opsForValue().set(key, status)
    }

    fun getQuizStatus(quizRoomId: Long): QuizStatusRedisDto {
        val key = "qr:$quizRoomId:status"
        val value =
            redisTemplate.opsForValue().get(key) ?: throw QuizStatusNotFoundInRedisException()
        return objectMapper.convertValue(value, QuizStatusRedisDto::class.java)
    }

    fun saveQuestionsAndAnswers(quizRoomId: Long, questions: MutableList<Question>) {
        questions.map {
            saveQuestion(quizRoomId, it.number, it)
            saveAnswer(quizRoomId, it.number, it.getAnswerSet())
        }
    }

    private fun saveQuestion(quizRoomId: Long, number: Long, question: Question) {
        val key = "qr:$quizRoomId:question:$number"
        val questionRedisDto = QuestionRedisDto.of(question)
        redisTemplate.opsForValue().set(key, questionRedisDto)
    }

    private fun saveAnswer(quizRoomId: Long, number: Long, answers: MutableSet<String>) {
        val key = "qr:$quizRoomId:answer:$number"
        redisTemplate.opsForValue().set(key, answers)
    }

    fun saveQuizParticipants(quizRoomId: Long, participants: MutableList<Participant>) {
        val key = "qr:$quizRoomId:users"
        val participantInfos = makeParticipantInfosByParticipants(participants)
        redisTemplate.opsForValue().set(key, participantInfos)
    }

    private fun makeParticipantInfosByParticipants(participants: MutableList<Participant>): MutableList<UserRedisDto> {
        return participants.map {
            UserRedisDto(it.user.id, it.position)
        } as MutableList<UserRedisDto>
    }
}