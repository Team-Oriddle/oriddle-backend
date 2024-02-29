package com.tuk.oriddle.domain.quizroom.dto.request

import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class QuizRoomCreateRequest(
    val quizId: Long,
    @field:NotBlank(message = "title은 비어 있을 수 없습니다.")
    val title: String?,
    @field:NotNull(message = "maxParticipant는 비어 있을 수 없습니다.")
    val maxParticipant: Integer?,
) {
    companion object {
        fun of(
            quiz: Quiz,
            quizRoomCreateRequest: QuizRoomCreateRequest
        ): QuizRoom {
            return quizRoomCreateRequest.let {
                QuizRoom(
                    quiz = quiz,
                    title = it.title!!,
                    maxParticipant = it.maxParticipant!!
                )
            }
        }
    }
}



