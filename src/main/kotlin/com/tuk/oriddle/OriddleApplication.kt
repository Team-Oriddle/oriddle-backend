package com.tuk.oriddle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class OriddleApplication

fun main(args: Array<String>) {
	runApplication<OriddleApplication>(*args)
}
