package com.swuweb.swuweb.application.dto.request

import jakarta.validation.constraints.NotBlank

data class AdminPasswordCheckRequest(
    @field:NotBlank
    val password: String
)