package br.com.alura.microservice.loja.controller.dto

import java.time.LocalDate

class InfoEntregaDTO(
	var pedidoId: Long? = null,
	var dataParaEntrega: LocalDate? = null,
	var enderecoOrigem: String? = null,
	var enderecoDestino: String? = null)