package com.tuk.oriddle.domain.quizroom.dto.message

data class JoinQuizRoomMessage(
    val userId: Long,
    val nickname: String,
    val position: Int
)