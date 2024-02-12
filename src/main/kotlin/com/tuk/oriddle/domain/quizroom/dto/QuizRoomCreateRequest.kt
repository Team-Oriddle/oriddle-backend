package com.tuk.oriddle.domain.quizroom.dto

import com.tuk.oriddle.domain.quiz.entity.Quiz
import com.tuk.oriddle.domain.quizroom.entity.QuizRoom

data class QuizRoomCreateRequest(
        val quizId: Long,
        val title: String,
        val maxParticipant: Integer,
        val questionCount: Integer
) {
    companion object {
        fun of(
                quiz: Quiz,
                quizRoomCreateRequest: QuizRoomCreateRequest
        ): QuizRoom {
            return QuizRoom(
                    quiz = quiz,
                    title = quizRoomCreateRequest.title,
                    maxParticipant = quizRoomCreateRequest.maxParticipant,
                    questionCount = quizRoomCreateRequest.questionCount
            )
        }
    }

}
