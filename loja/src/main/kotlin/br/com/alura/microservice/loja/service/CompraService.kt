package br.com.alura.microservice.loja.service

import org.springframework.stereotype.Service
import br.com.alura.microservice.loja.controller.dto.CompraDTO
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpMethod
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO
import org.springframework.http.ResponseEntity
import org.springframework.beans.factory.annotation.Autowired

@Service
class CompraService {
	@Autowired private lateinit var restTemplate: RestTemplate

	fun realizaCompra(compra: CompraDTO) {
		val response: ResponseEntity<InfoFornecedorDTO> = restTemplate.exchange(
			"http://fornecedor/info/${compra.endereco.estado}",
			HttpMethod.GET,
			null,
			InfoFornecedorDTO::class.java);

		System.out.println("Endereco: ${response.getBody()?.endereco}")
	}
}