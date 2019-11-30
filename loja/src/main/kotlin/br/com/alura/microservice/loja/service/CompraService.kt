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
import br.com.alura.microservice.loja.controller.dto.InfoEntregaDTO
import br.com.alura.microservice.loja.controller.dto.VoucherDTO
import br.com.alura.microservice.loja.client.TransportadorClient
import java.time.LocalDate
import br.com.alura.microservice.loja.controller.dto.ItemDaCompraDTO
import br.com.alura.microservice.loja.controller.dto.DadosFornecedorDTO

@Service
open class CompraService {
	@Autowired lateinit var fornecedorClient: FornecedorClient
	@Autowired lateinit var transportadorClient: TransportadorClient
	@Autowired lateinit var compraRepository: CompraRepository

	@HystrixCommand(threadPoolKey = "buscaCompraThreadPool")
	open fun buscaCompra(id: Long) = compraRepository.findById(id).orElse(Compra())
	
	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
	open fun realizaCompra(compra: CompraDTO): Compra {
		val dadosFornecedor = this.obterDadosFornecedorDTO(compra)
		val voucher = this.obterDadosTransportador(dadosFornecedor, compra)

		return this.salvarCompra(dadosFornecedor, compra, voucher)
	}

	open fun realizaCompraFallback(compra: CompraDTO): Compra {
		return Compra()
	}
	
	private fun obterDadosFornecedorDTO(compra: CompraDTO): DadosFornecedorDTO {
		val info = fornecedorClient.getInfoPor(compra.endereco.estado)
		val pedido = fornecedorClient.realizaPedido(compra.itens)

		return DadosFornecedorDTO(info, pedido)
	}
	
	private fun obterDadosTransportador(dadosFornecedor: DadosFornecedorDTO, compra: CompraDTO): VoucherDTO {
		val entrega = InfoEntregaDTO(
			pedidoId = dadosFornecedor.pedido.id,
			dataParaEntrega = LocalDate.now().plusDays(dadosFornecedor.pedido.tempoDePreparo.toLong()),
			enderecoOrigem = dadosFornecedor.info.endereco,
			enderecoDestino = compra.endereco.toString())

		return transportadorClient.reservaEntrega(entrega)
	}
	
	private fun salvarCompra(dadosFornecedor: DadosFornecedorDTO, compra: CompraDTO, voucher: VoucherDTO): Compra {
		val compraSalva = Compra(
			pedidoId = dadosFornecedor.pedido.id,
			tempoDePreparo = dadosFornecedor.pedido.tempoDePreparo,
			enderecoDestino = compra.endereco.toString(),
			dataParaEntrega = voucher.previsaoParaEntrega,
			voucher = voucher.numero)

		return compraRepository.save(compraSalva)
	}
}