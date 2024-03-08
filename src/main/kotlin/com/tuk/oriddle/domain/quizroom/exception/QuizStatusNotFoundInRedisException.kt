package com.tuk.oriddle.domain.quizroom.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

// TODO: 패키지 바꾸기
class QuizStatusNotFoundInRedisException : BusinessException {
    constructor() : super(ErrorCode.QUIZ_STATUS_NOT_FOUND_IN_REDIS)
}