package br.com.alura.microservice.loja.controller

import br.com.alura.microservice.loja.controller.dto.CompraDTO
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO
import br.com.alura.microservice.loja.service.CompraService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/compra")
class CompraController {
	@Autowired lateinit var compraService: CompraService

	@PostMapping
	fun realizaCompra(@RequestBody compra: CompraDTO) {
		compraService.realizaCompra(compra)
	}
}