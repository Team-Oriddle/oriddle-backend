package com.tuk.oriddle.global.error

import org.springframework.validation.BindingResult

data class ErrorResponse private constructor(
    val businessCode: String,
    val errorMessage: String,
    val errors: List<FieldError> = listOf()
) {
    companion object {
        fun of(code: ErrorCode, bindingResult: BindingResult): ErrorResponse {
            val fieldErrors = bindingResult.fieldErrors.map { error ->
                FieldError(
                    field = error.field,
                    value = error.rejectedValue?.toString() ?: "",
                    reason = error.defaultMessage ?: ""
                )
            }
            return ErrorResponse(
                businessCode = code.code,
                errorMessage = code.message,
                errors = fieldErrors
            )
        }

        fun of(code: ErrorCode): ErrorResponse = ErrorResponse(
            businessCode = code.code,
            errorMessage = code.message
        )

        fun fieldNullErrorResponse(fieldName: String): ErrorResponse = ErrorResponse(
            businessCode = ErrorCode.INPUT_INVALID_VALUE.code,
            errorMessage = "${fieldName}이(가) null 입니다."
        )

        fun fieldTypeErrorResponse(fieldName: String, type: String): ErrorResponse = ErrorResponse(
            businessCode = ErrorCode.INPUT_INVALID_VALUE.code,
            errorMessage = "${fieldName}의 type은 ${type}입니다."
        )
    }

    data class FieldError(
        val field: String,
        val value: String,
        val reason: String
    )
}
