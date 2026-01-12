package com.swuweb.swuweb.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "answer")
class Answer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var questionNum: Long = 0,

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String = ""
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_share_id", nullable = false)
    lateinit var applicationShare: ApplicationShare
}
