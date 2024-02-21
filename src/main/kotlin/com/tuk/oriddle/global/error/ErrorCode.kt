package com.tuk.oriddle.global.error

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // Global (GL)
    INTERNAL_SERVER_ERROR(500, "GL0001", "서버 오류"),
    INPUT_INVALID_VALUE(409, "GL0002", "잘못된 입력"),

    // Quiz (QZ)
    QUIZ_NOT_FOUND(400, "QZ0001", "퀴즈를 찾을 수 없음"),

    // Quiz (QR)
    QUIZ_ROOM_NOT_FOUND(400, "QR0001", "퀴즈방을 찾을 수 없음"),

    // User (US)
    USER_NOT_FOUND(400,"US001","유저를 찾을 수 없음"),

    // Participant (PC)
    PARTICIPANT_NOT_FOUND(400,"PC001","참가자를 찾을 수 없음"),
    ALREADY_PARTICIPATING(400,"PC001","이미 참여중 입니다.")
}