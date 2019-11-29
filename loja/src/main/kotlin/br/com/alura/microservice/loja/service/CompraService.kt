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
import br.com.alura.microservice.loja.repository.CompraRepository
import java.util.Optional
import java.lang.Exception

@Service
open class CompraService {
	@Autowired lateinit var fornecedorClient: FornecedorClient
	@Autowired lateinit var compraRepository: CompraRepository

	@HystrixCommand(threadPoolKey = "buscaCompraThreadPool")
	open fun buscaCompra(id: Long) = compraRepository.findById(id).orElse(Compra())
	
	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
	open fun realizaCompra(compra: CompraDTO): Compra {
		val estado = compra.endereco.estado

		val info = fornecedorClient.getInfoPor(estado)
		val pedido = fornecedorClient.realizaPedido(compra.itens)

		val compraSalva = Compra(pedido.id, pedido.tempoDePreparo, compra.endereco.toString())
		compraRepository.save(compraSalva)

		return compraSalva
	}

	open fun realizaCompraFallback(compra: CompraDTO): Compra {
		return Compra()
	}
}