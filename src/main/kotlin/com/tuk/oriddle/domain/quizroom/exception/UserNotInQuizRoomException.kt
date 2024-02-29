package com.tuk.oriddle.domain.quizroom.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class UserNotInQuizRoomException : BusinessException {
    constructor() : super(ErrorCode.USER_NOT_IN_QUIZ_ROOM)
}