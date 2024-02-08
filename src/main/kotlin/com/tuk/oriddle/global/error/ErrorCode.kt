package com.tuk.oriddle.global.error

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // Global (GL)
    INTERNAL_SERVER_ERROR(500, "GL0001", "서버 오류"),
    INPUT_INVALID_VALUE(409, "GL0002", "잘못된 입력"),

    // Quiz (QZ)
    QUIZ_NOT_FOUND(400, "QZ0001", "퀴즈를 찾을 수 없음");
}