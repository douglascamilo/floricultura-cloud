package br.com.alura.microservice.loja.service

import org.springframework.stereotype.Service
import br.com.alura.microservice.loja.controller.dto.CompraDTO
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpMethod
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO
import org.springframework.http.ResponseEntity

@Service
class CompraService {

	fun realizaCompra(compra: CompraDTO) {
		val response: ResponseEntity<InfoFornecedorDTO> = RestTemplate().exchange(
			"http://localhost:8081/info/${compra.endereco.estado}",
			HttpMethod.GET,
			null,
			InfoFornecedorDTO::class.java);

		
	}
}