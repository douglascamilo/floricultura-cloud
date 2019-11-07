package br.com.alura.microservice.loja.service

import org.springframework.stereotype.Service
import br.com.alura.microservice.loja.controller.dto.CompraDTO
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpMethod
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO
import org.springframework.http.ResponseEntity
import org.springframework.beans.factory.annotation.Autowired
import br.com.alura.microservice.loja.client.FornecedorClient

@Service
class CompraService {
	@Autowired private lateinit var fornecedorClient: FornecedorClient

	fun realizaCompra(compra: CompraDTO) {
		val response = fornecedorClient.getInfoPor(compra.endereco.estado)

		System.out.println("Endereco: ${response.endereco}")
	}
}