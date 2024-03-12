package com.tuk.oriddle.domain.participant.exception

import com.tuk.oriddle.global.error.ErrorCode
import com.tuk.oriddle.global.error.exception.BusinessException

class ParticipantNotHostException : BusinessException {
    constructor() : super(ErrorCode.PARTICIPANT_NOT_HOST)
}