package com.tuk.oriddle.global.error.exception

import com.tuk.oriddle.global.error.ErrorCode

class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)