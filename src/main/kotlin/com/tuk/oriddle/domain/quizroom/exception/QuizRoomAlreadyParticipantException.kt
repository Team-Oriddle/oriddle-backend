package com.tuk.oriddle.domain.quizroom.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class QuizRoomAlreadyParticipantException : BusinessException {
    constructor() : super(ErrorCode.QUIZ_ROOM_ALREADY_PARTICIPANT)
}