package com.tuk.oriddle.domain.quiz.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class QuizNotFoundException: BusinessException(ErrorCode.QUIZ_NOT_FOUND)