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
import br.com.alura.microservice.loja.model.Compra
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/compra")
class CompraController {
	private val LOG = LoggerFactory.getLogger(CompraController::class.java)

	@Autowired lateinit var compraService: CompraService

	@PostMapping
	fun realizaCompra(@RequestBody compra: CompraDTO): Compra {
		LOG.info("Realizando compra: ${compra}")
		return compraService.realizaCompra(compra)
	}

	@GetMapping("/{id}")
	fun buscaCompra(@PathVariable("id") id: Long): Compra {
		return compraService.buscaCompra(id);
	}
}