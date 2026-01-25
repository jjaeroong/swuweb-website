package com.swuweb.swuweb.application.service


import com.swuweb.swuweb.application.dto.request.CreateApplicationShareRequest
import com.swuweb.swuweb.application.dto.response.*
import com.swuweb.swuweb.domain.entity.Answer
import com.swuweb.swuweb.domain.entity.ApplicationShare
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

    @Transactional
    fun deleteById(applicationId: Long) {
        if (!repository.existsById(applicationId)) {
            throw IllegalArgumentException("지원서가 존재하지 않습니다. id=$applicationId")
        }
        repository.deleteById(applicationId)
    }

    fun getAllLinksForAdmin(): AdminApplicationLinksResponse {
        val apps = repository.findAllWithAnswersOrderByCreatedAtDesc()

        val items = apps.map { app ->
            val nameAnswer = app.answers.find { it.questionNum == 0L }

            AdminApplicationLinkItemResponse(
                applicationShareId = app.id,
                name = nameAnswer?.content ?: "이름 없음",
                link = "https://swuweb-homepage.vercel.app/public/application/${app.shareToken}"
            )

        }

        return AdminApplicationLinksResponse(applications = items)
    }
}

