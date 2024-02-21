package com.tuk.oriddle.domain.user.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class UserNotFoundException : BusinessException {
    constructor() : super(ErrorCode.USER_NOT_FOUND)
}