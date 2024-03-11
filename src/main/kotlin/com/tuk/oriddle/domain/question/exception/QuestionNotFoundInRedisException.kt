package com.tuk.oriddle.domain.question.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class QuestionNotFoundInRedisException : BusinessException {
    constructor() : super(ErrorCode.QUESTION_NOT_FOUND_IN_REDIS)
}