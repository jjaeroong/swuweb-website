package com.swuweb.swuweb.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "application_share",
    indexes = [Index(name = "idx_app_share_token", columnList = "shareToken", unique = true)]
)
class ApplicationShare(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 64)
    var shareToken: String = UUID.randomUUID().toString().replace("-", ""),

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(
        mappedBy = "applicationShare",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val answers: MutableList<Answer> = mutableListOf()
) {
    fun addAnswer(answer: Answer) {
        answers.add(answer)
        answer.applicationShare = this
    }
}