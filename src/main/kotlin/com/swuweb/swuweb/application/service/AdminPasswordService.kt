package com.swuweb.swuweb.application.service

import com.swuweb.swuweb.application.dto.request.AdminPasswordCheckRequest
import com.swuweb.swuweb.application.dto.response.AdminPasswordCheckResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AdminPasswordService(
    @Value("\${app.admin.password}")
    private val adminPassword: String
) {
    fun checkPassword(req: AdminPasswordCheckRequest): AdminPasswordCheckResponse {
        return AdminPasswordCheckResponse(
            valid = req.password == adminPassword
        )
    }
}