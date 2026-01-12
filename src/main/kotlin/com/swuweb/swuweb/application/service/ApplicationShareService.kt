package com.swuweb.swuweb.application.service


import com.swuweb.swuweb.application.dto.request.CreateApplicationShareRequest
import com.swuweb.swuweb.application.dto.response.CreateApplicationShareResponse
import com.swuweb.swuweb.domain.entity.Answer
import com.swuweb.swuweb.domain.entity.ApplicationShare
import com.swuweb.swuweb.application.dto.response.ApplicationSharePublicResponse
import com.swuweb.swuweb.application.dto.response.AnswerPublicItemResponse

import com.swuweb.swuweb.domain.repository.ApplicationShareRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApplicationShareService(
    private val repository: ApplicationShareRepository,
    @Value("\${app.share.base-url:http://localhost:8080}") private val baseUrl: String
) {
    @Transactional
    fun create(req: CreateApplicationShareRequest): CreateApplicationShareResponse {
        val app = ApplicationShare()

        req.answers
            .sortedBy { it.questionNum }
            .forEach { item ->
                app.addAnswer(
                    Answer(
                        questionNum = item.questionNum,
                        content = item.content
                    )
                )
            }

        // 토큰 충돌 방지(거의 없지만 안전)
        while (repository.existsByShareToken(app.shareToken)) {
            app.shareToken = UUID.randomUUID().toString().replace("-", "")
        }

        val saved = repository.save(app)
        return CreateApplicationShareResponse(
            applicationId = saved.id,
            shareUrl = "$baseUrl/public/applications/${saved.shareToken}"
        )
    }

    @Transactional
    fun getPublicByToken(token: String): ApplicationSharePublicResponse {
        val app = repository.findByShareToken(token)
            .orElseThrow { IllegalArgumentException("Invalid token") }

        return ApplicationSharePublicResponse(
            applicationId = app.id,
            createdAt = app.createdAt,
            answers = app.answers
                .sortedBy { it.questionNum }
                .map { a ->
                    AnswerPublicItemResponse(
                        questionNum = a.questionNum,
                        content = a.content
                    )
                }
        )
    }
}

