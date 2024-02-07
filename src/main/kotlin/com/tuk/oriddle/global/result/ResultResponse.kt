package com.tuk.oriddle.global.result

class ResultResponse private constructor(
    val code: String,
    val message: String,
    val data: Any?
) {
    companion object {
        fun of(resultCode: ResultCode, data: Any? = null): ResultResponse =
            ResultResponse(resultCode.code, resultCode.message, data)
    }
}