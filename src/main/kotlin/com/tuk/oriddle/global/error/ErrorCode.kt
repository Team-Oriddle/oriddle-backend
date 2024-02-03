package com.tuk.oriddle.global.error

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // 도메인 별로 나눠서 관리
    // Global
    INTERNAL_SERVER_ERROR(500, "GL0001", "서버 오류"),
    INPUT_INVALID_VALUE(409, "GL0002", "잘못된 입력"),
    ;
}