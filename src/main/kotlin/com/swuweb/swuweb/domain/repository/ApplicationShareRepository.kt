package com.swuweb.swuweb.domain.repository

import com.swuweb.swuweb.domain.entity.ApplicationShare
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ApplicationShareRepository : JpaRepository<ApplicationShare, Long> {
    fun findByShareToken(shareToken: String): Optional<ApplicationShare>
    fun existsByShareToken(shareToken: String): Boolean
}