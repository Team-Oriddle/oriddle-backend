package com.tuk.oriddle.global.result


enum class ResultCode(val code: String, val message: String) {
    // 도메인 별로 나눠서 관리
    // Quiz
    QUIZ_GET_INFO_SUCCESS("QUIZ001", "퀴즈 정보 조회 성공")
    ;
}