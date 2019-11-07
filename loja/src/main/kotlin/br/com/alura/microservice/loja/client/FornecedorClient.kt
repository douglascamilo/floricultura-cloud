package br.com.alura.microservice.loja.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO
import org.springframework.web.bind.annotation.RequestMapping
import br.com.alura.microservice.loja.controller.dto.ItemDaCompraDTO
import br.com.alura.microservice.loja.controller.dto.InfoPeditoDTO
import org.springframework.web.bind.annotation.PostMapping

@FeignClient("fornecedor")
interface FornecedorClient {

	@RequestMapping("/info/{estado}")
	fun getInfoPor(@PathVariable estado: String): InfoFornecedorDTO

	@PostMapping("/pedido")
	fun realizaPedido(itens: List<ItemDaCompraDTO>): InfoPeditoDTO
}