package com.tuk.oriddle.domain.quizroom.dto.request

import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range

data class QuizRoomCreateRequest constructor(
    val quizId: Long,
    @field:NotBlank
    @field:Length(max = 20)
    val title: String,
    @field:Range(min = 2, max = 8)
    val maxParticipant: Int,
) {
    fun toEntity(quiz: Quiz): QuizRoom {
        return QuizRoom(this.title, this.maxParticipant, quiz)
    }
}
