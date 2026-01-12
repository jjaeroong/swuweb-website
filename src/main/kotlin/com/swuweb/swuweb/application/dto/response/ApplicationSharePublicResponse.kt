package com.swuweb.swuweb.application.dto.response

import java.time.LocalDateTime

data class ApplicationSharePublicResponse(
    val applicationId: Long,
    val createdAt: LocalDateTime,
    val answers: List<AnswerPublicItemResponse>
)

data class AnswerPublicItemResponse(
    val questionNum: Long,
    val content: String
)