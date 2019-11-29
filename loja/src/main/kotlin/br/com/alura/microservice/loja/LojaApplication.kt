package br.com.alura.microservice.loja

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
open class LojaApplication

fun main(args: Array<String>) {
	runApplication<LojaApplication>(*args)
}
