package com.tuk.oriddle.domain.quizroom.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class QuizRoomFullException : BusinessException {
    constructor() : super(ErrorCode.QUIZ_ROOM_FULL)
}