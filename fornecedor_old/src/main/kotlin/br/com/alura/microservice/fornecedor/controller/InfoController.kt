package br.com.alura.microservice.fornecedor.controller

import br.com.alura.microservice.fornecedor.model.InfoFornecedor
import br.com.alura.microservice.fornecedor.service.InfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/info")
class InfoController {
	@Autowired lateinit var infoService: InfoService

	@GetMapping("/{estado}")
	fun getInfoPor(@PathVariable estado: String): InfoFornecedor {
		return infoService.getInfoPor(estado)
	}
}