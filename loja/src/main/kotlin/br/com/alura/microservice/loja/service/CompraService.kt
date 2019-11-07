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
import org.slf4j.LoggerFactory

@Service
class CompraService {
	private val LOG = LoggerFactory.getLogger(CompraService::class.java)

	@Autowired private lateinit var fornecedorClient: FornecedorClient

	fun realizaCompra(compra: CompraDTO): Compra {
		val estado = compra.endereco.estado
		
		LOG.info("Buscando informacoes de fornecedor de ${estado}")
		val info = fornecedorClient.getInfoPor(estado)

		LOG.info("Realizando um pedido...")
		val pedido = fornecedorClient.realizaPedido(compra.itens)

		return Compra(pedido.id, pedido.tempoDePreparo, compra.endereco.toString());
	}
}