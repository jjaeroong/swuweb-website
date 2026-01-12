package com.swuweb.swuweb.presentation

import com.swuweb.swuweb.application.dto.request.CreateApplicationShareRequest
import com.swuweb.swuweb.application.dto.response.ApplicationSharePublicResponse
import com.swuweb.swuweb.application.dto.response.CreateApplicationShareResponse
import com.swuweb.swuweb.application.service.ApplicationShareService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/application")
class ApplicationShareController(
    private val service: ApplicationShareService
) {

    /**
     * 지원자가 여러 질문 답변을 한 번에 저장하고,
     * 공유 링크(URL)를 반환
     */
    @PostMapping("/")
    fun create(
        @RequestBody @Valid req: CreateApplicationShareRequest
    ): CreateApplicationShareResponse {
        return service.create(req)
    }

    /**
     * 공유 링크(token)로 저장된 답변 묶음 조회 (JSON)
     * 프론트가 이 데이터를 받아서 화면 렌더링
     */
    @GetMapping("/{token}")
    fun getPublic(
        @PathVariable token: String
    ): ApplicationSharePublicResponse {
        return service.getPublicByToken(token)
    }
}