package br.com.alura.microservice.loja.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO
import org.springframework.web.bind.annotation.RequestMapping

@FeignClient("fornecedor")
interface FornecedorClient {

	@RequestMapping("/info/{estado}")
	fun getInfoPor(@PathVariable estado: String): InfoFornecedorDTO;

	@RequestMapping("/info/test/{estado}")
	fun teste(@PathVariable estado: String): InfoFornecedorDTO;
}