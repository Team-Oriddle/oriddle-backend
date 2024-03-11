package com.tuk.oriddle.global.error

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // Global (GL)
    INTERNAL_SERVER_ERROR(500, "GL0001", "서버 오류"),
    INPUT_INVALID_VALUE(409, "GL0002", "잘못된 입력"),
    ACCESS_DENIED(401, "GL0003", "로그인이 필요합니다."),

    // Quiz (QZ)
    QUIZ_NOT_FOUND(400, "QZ0001", "퀴즈를 찾을 수 없음"),

    // QuizRoom (QR)
    QUIZ_ROOM_NOT_FOUND(400, "QR0001", "퀴즈방을 찾을 수 없음"),
    QUIZ_ROOM_IS_FULL(400, "QR0002", "퀴즈방이 꽉 차 있음"),
    QUIZ_ROOM_STATUS_NOT_FOUND_IN_REDIS(400, "QR0003", "퀴즈방 진행 상태가 Redis에 없음"),

    // User (US)
    USER_NOT_FOUND(400, "US0001", "유저를 찾을 수 없음"),

    // Participant (PC)
    PARTICIPANT_NOT_FOUND(400, "PC0001", "참가자를 찾을 수 없음"),
    QUIZ_ROOM_ALREADY_PARTICIPANT(400, "PC0002", "이미 퀴즈방에 참가중"),
    USER_NOT_IN_QUIZ_ROOM(400, "PC0003", "사용자가 퀴즈방에 없음"),

    // Question (QS)
    QUESTION_NOT_FOUND_IN_REDIS(400, "QU0001", "질문이 Redis에 없음"),

    // Answer (AN)
    ANSWER_NOT_FOUND_IN_REDIS(400, "AN0001", "답변이 Redis에 없음"),
}