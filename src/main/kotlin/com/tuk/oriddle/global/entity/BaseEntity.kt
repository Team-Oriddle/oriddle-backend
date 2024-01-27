package com.tuk.oriddle.global.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        private set

    @CreatedDate
    @Column(nullable = false)
    lateinit var createdAt: LocalDateTime
        private set

    @LastModifiedDate
    @Column(nullable = false)
    lateinit var updatedAt: LocalDateTime
        private set
}