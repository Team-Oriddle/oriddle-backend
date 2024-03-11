package com.tuk.oriddle.domain.quizroom.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class QuizRoomStatusNotFoundInRedisException : BusinessException {
    constructor() : super(ErrorCode.QUIZ_ROOM_STATUS_NOT_FOUND_IN_REDIS)
}