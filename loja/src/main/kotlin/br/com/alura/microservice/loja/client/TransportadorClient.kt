package br.com.alura.microservice.loja.client

import org.springframework.cloud.openfeign.FeignClient
import br.com.alura.microservice.loja.controller.dto.VoucherDTO
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import br.com.alura.microservice.loja.controller.dto.InfoEntregaDTO

@FeignClient("transportador")
interface TransportadorClient {

	@PostMapping("/entrega")
	fun reservaEntrega(entrega: InfoEntregaDTO): VoucherDTO
}