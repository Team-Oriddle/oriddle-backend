package com.tuk.oriddle.global.error

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // Global (GL)
    INTERNAL_SERVER_ERROR(500, "GL0001", "서버 오류가 발생했습니다."),
    INPUT_INVALID_VALUE(400, "GL0002", "잘못된 입력입니다."),
    ACCESS_DENIED(401, "GL0003", "로그인이 필요합니다."),

    // Quiz (QZ)
    QUIZ_NOT_FOUND(404, "QZ0001", "퀴즈를 찾을 수 없습니다."),

    // QuizRoom (QR)
    QUIZ_ROOM_NOT_FOUND(404, "QR0001", "퀴즈방을 찾을 수 없습니다."),
    QUIZ_ROOM_IS_FULL(403, "QR0002", "퀴즈방이 꽉 차 있습니다."),
    QUIZ_ROOM_STATUS_NOT_FOUND_IN_REDIS(404, "QR0003", "퀴즈방 진행 상태가 Redis에 없습니다."),

    // User (US)
    USER_NOT_FOUND(404, "US0001", "유저를 찾을 수 없습니다."),

    // Participant (PC)
    PARTICIPANT_NOT_FOUND(404, "PC0001", "참가자를 찾을 수 없습니다."),
    QUIZ_ROOM_ALREADY_PARTICIPANT(409, "PC0002", "이미 현재 퀴즈방에 참가중입니다."),
    USER_NOT_IN_QUIZ_ROOM(404, "PC0003", "유저가 퀴즈방에 없습니다."),
    PARTICIPANT_NOT_HOST(403, "PC0004", "참가자가 방장이 아닙니다."),
    USER_ALREADY_IN_QUIZ_ROOM(409, "PC0005", "유저가 이미 다른 퀴즈방에 참가중입니다."),

    // Question (QS)
    QUESTION_NOT_FOUND_IN_REDIS(404, "QU0001", "질문이 Redis에 없습니다."),

    // Answer (AN)
    ANSWER_NOT_FOUND_IN_REDIS(404, "AN0001", "답변이 Redis에 없습니다."),
}