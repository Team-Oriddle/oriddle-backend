package com.tuk.oriddle.global.error

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // Global (GL)
    INTERNAL_SERVER_ERROR(500, "GL0001", "서버 오류"),
    INPUT_INVALID_VALUE(409, "GL0002", "잘못된 입력"),
    ACCESS_DENIED(401, "GL0003", "로그인이 필요합니다."),

    // Quiz (QZ)
    QUIZ_NOT_FOUND(400, "QZ0001", "퀴즈를 찾을 수 없음"),

    // Quiz (QR)
    QUIZ_ROOM_NOT_FOUND(400, "QR0001", "퀴즈방을 찾을 수 없음"),
    QUIZ_ROOM_IS_FULL(400, "QR0002", "퀴즈방이 꽉 차 있음"),

    // User (US)
    USER_NOT_FOUND(400,"US0001","유저를 찾을 수 없음"),

    // Participant (PC)
    PARTICIPANT_NOT_FOUND(400,"PC0001","참가자를 찾을 수 없음"),
    QUIZ_ROOM_ALREADY_PARTICIPANT(400, "PC0002", "이미 퀴즈방에 참가중"),
}