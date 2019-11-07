package br.com.alura.microservice.loja.service

import org.springframework.stereotype.Service
import br.com.alura.microservice.loja.controller.dto.CompraDTO
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpMethod
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO
import org.springframework.http.ResponseEntity
import org.springframework.beans.factory.annotation.Autowired
import br.com.alura.microservice.loja.client.FornecedorClient
import br.com.alura.microservice.loja.model.Compra

@Service
class CompraService {
	@Autowired private lateinit var fornecedorClient: FornecedorClient

	fun realizaCompra(compra: CompraDTO): Compra {
		val info = fornecedorClient.getInfoPor(compra.endereco.estado)
		val pedido = fornecedorClient.realizaPedido(compra.itens)

		return Compra(pedido.id, pedido.tempoDePreparo, compra.endereco.toString());
	}
}