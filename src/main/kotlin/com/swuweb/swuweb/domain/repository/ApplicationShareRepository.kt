package com.swuweb.swuweb.domain.repository

import com.swuweb.swuweb.domain.entity.ApplicationShare
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ApplicationShareRepository : JpaRepository<ApplicationShare, Long> {
    fun findByShareToken(shareToken: String): Optional<ApplicationShare>

    fun existsByShareToken(shareToken: String): Boolean

    @Query("select distinct a from ApplicationShare a left join fetch a.answers order by a.createdAt desc")
    fun findAllWithAnswersOrderByCreatedAtDesc(): List<ApplicationShare>
}