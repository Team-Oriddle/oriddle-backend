package com.tuk.oriddle.global.result

enum class ResultCode(val code: String, val message: String) {
    // 도메인 별로 나눠서 관리
    // Quiz
    QUIZ_CREATE_SUCCESS("QZ0001", "퀴즈 생성 성공"),
    ;
}