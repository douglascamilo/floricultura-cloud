package br.com.alura.microservice.fornecedor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FornecedorApplication

fun main(args: Array<String>) {
	runApplication<FornecedorApplication>(*args)
}
