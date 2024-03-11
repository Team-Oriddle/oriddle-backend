package com.tuk.oriddle.domain.answer.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class AnswerNotFoundInRedisException : BusinessException {
    constructor() : super(ErrorCode.ANSWER_NOT_FOUND_IN_REDIS)
}