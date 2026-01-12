package com.swuweb.swuweb

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("SWUWEB 지원서 API")
                    .description("지원자가 답변을 저장하고 공유 토큰으로 조회하는 API 문서")
                    .version("v1")
                    .license(License().name("MIT"))
            )
            .servers(
                listOf(
                    Server().url("https://swuweb-website-production.up.railway.app")
                )
            )
    }
}
