package com.swuweb.swuweb.presentation

import com.swuweb.swuweb.application.dto.request.AdminPasswordCheckRequest
import com.swuweb.swuweb.application.dto.response.AdminApplicationLinksResponse
import com.swuweb.swuweb.application.dto.response.AdminPasswordCheckResponse
import com.swuweb.swuweb.application.service.AdminPasswordService
import com.swuweb.swuweb.application.service.ApplicationShareService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(

    private val adminPasswordService: AdminPasswordService,
    private val service: ApplicationShareService
) {

    @PostMapping("/password/check")
    fun checkPassword(
        @RequestBody @Valid req: AdminPasswordCheckRequest
    ): AdminPasswordCheckResponse {
        return adminPasswordService.checkPassword(req)
    }

    @GetMapping("/admin/applications")
    fun getAllLinksForAdmin(): AdminApplicationLinksResponse {
        return service.getAllLinksForAdmin()
    }
}