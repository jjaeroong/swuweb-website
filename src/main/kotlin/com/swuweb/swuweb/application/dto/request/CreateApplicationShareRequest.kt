package com.swuweb.swuweb.application.dto.request

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CreateApplicationShareRequest(
    @field:NotEmpty
    @field:Valid
    val answers: List<AnswerItemRequest>
)

data class AnswerItemRequest(
    val questionNum: Long,

    @field:NotBlank
    @field:Size(max = 12000)
    val content: String
)
