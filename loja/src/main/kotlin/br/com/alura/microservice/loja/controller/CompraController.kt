package br.com.alura.microservice.loja.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import br.com.alura.microservice.loja.controller.dto.CompraDTO
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/compra")
class CompraController {

	@PostMapping
	fun realizaCompra(@RequestBody compra: CompraDTO) {
	}
}