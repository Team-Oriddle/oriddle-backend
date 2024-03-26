package com.tuk.oriddle.domain.quizroom.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class UserAlreadyInOtherQuizRoomException : BusinessException {
    constructor() : super(ErrorCode.USER_ALREADY_IN_OTHER_QUIZ_ROOM)
}