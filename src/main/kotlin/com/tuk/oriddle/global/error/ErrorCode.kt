package com.tuk.oriddle.global.error

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // 도메인 별로 나눠서 관리
    // Global
    INTERNAL_SERVER_ERROR(500, "GL0001", "서버 오류"),
    INPUT_INVALID_VALUE(409, "GL0002", "잘못된 입력"),

    // User
    USER_NOT_FOUND(404, "US0001", "유저를 찾을 수 없습니다."),

    // Quiz
    QUIZ_NOT_FOUND(404, "QZ0001", "퀴즈를 찾을 수 없습니다."),
    ;
}