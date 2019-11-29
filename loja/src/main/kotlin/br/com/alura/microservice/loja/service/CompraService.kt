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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand

@Service
open class CompraService {
//	private val LOG = LoggerFactory.getLogger(CompraService::class.java)

	@Autowired lateinit var fornecedorClient: FornecedorClient

	@HystrixCommand(fallbackMethod = "realizaCompraFallback")
	open fun realizaCompra(compra: CompraDTO): Compra {
		val estado = compra.endereco.estado

//		LOG.info("Buscando informacoes de fornecedor de ${estado}")
		val info = fornecedorClient.getInfoPor(estado)

//		LOG.info("Realizando um pedido...")
		val pedido = fornecedorClient.realizaPedido(compra.itens)
		
		Thread.sleep(2000);

		return Compra(pedido.id, pedido.tempoDePreparo, compra.endereco.toString())
	}

	open fun realizaCompraFallback(compra: CompraDTO): Compra {
		return Compra(0, 0, "Deu ruim...")
	}
}