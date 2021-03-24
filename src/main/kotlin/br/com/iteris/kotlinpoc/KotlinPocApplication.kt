package br.com.iteris.kotlinpoc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class KotlinPocApplication

fun main(args: Array<String>) {
	runApplication<KotlinPocApplication>(*args)
}
