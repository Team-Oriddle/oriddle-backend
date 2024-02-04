package com.tuk.oriddle.global.error.exception

import com.tuk.oriddle.global.error.ErrorCode

open class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)