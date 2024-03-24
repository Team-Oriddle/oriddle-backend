package com.tuk.oriddle.domain.quizroom.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class UserAlreadyInCurrentQuizRoomException : BusinessException {
    constructor() : super(ErrorCode.USER_ALREADY_IN_CURRENT_QUIZ_ROOM)
}