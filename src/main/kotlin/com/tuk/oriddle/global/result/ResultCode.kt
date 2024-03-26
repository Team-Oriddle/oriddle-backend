package com.tuk.oriddle.global.result

enum class ResultCode(val code: String, val message: String) {
    // Quiz (QZ)
    QUIZ_GET_INFO_SUCCESS("QZ0001", "퀴즈 정보 조회를 성공했습니다."),
    QUIZ_PAGING_GET_SUCCESS("QZ0002", "퀴즈 페이지 조회를 성공했습니다."),

    // QuizRoom (QR)
    QUIZ_ROOM_CREATE_SUCCESS("QR0001", "퀴즈방 생성을 성공했습니다."),
    QUIZ_ROOM_JOIN_SUCCESS("QR0002", "퀴즈방 참여를 성공했습니다."),
    QUIZ_ROOM_LEAVE_SUCCESS("QR0003", "퀴즈방 퇴장을 성공했습니다."),
    QUIZ_ROOM_GET_INFO_SUCCESS("QR0004", "퀴즈방 정보 조회를 성공했습니다."),
    QUIZ_ROOM_START_SUCCESS("QR0005", "퀴즈 시작을 성공했습니다."),

    // User (US)
    USER_GET_SUCCESS("US0001", "유저 정보 조회를 성공했습니다."),
    USER_NICKNAME_UPDATE_SUCCESS("US0002", "유저 닉네임 수정을 성공했습니다.")
}