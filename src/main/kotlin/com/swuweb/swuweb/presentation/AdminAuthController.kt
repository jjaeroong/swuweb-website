package com.swuweb.swuweb.presentation

import com.swuweb.swuweb.application.dto.request.AdminPasswordCheckRequest
import com.swuweb.swuweb.application.dto.response.AdminPasswordCheckResponse
import com.swuweb.swuweb.application.service.AdminPasswordService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminPasswordService: AdminPasswordService
) {

    @PostMapping("/password/check")
    fun checkPassword(
        @RequestBody @Valid req: AdminPasswordCheckRequest
    ): AdminPasswordCheckResponse {
        return adminPasswordService.checkPassword(req)
    }
}