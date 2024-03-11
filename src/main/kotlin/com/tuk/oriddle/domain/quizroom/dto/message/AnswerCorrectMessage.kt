package com.tuk.oriddle.domain.quizroom.dto.message

data class AnswerCorrectMessage(
    val userId: Long,
    val answer: String,
    val score: Int
)
