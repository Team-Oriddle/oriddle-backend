package com.tuk.oriddle.domain.quiz.dto

import jakarta.validation.constraints.NotBlank

data class QuizCreateRequest(
    @field:NotBlank
    val title: String,
    val description: String,
    val imageUrl: String,
    val makerId: Long
)