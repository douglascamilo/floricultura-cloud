package br.com.alura.microservice.loja

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class LojaApplication

fun main(args: Array<String>) {
	runApplication<LojaApplication>(*args)
}
