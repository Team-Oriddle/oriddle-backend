package com.tuk.oriddle.global.result

enum class ResultCode(val code: String, val message: String) {
    // Quiz (QZ)
    QUIZ_GET_INFO_SUCCESS("QZ0001", "퀴즈 정보 조회 성공"),
    QUIZ_PAGING_GET_SUCCESS("QZ0002", "퀴즈 페이지 조회 성공"),

    // QuizRoom (QR)
    QUIZ_ROOM_CREATE_SUCCESS("QR0001", "퀴즈방 생성 성공")
}