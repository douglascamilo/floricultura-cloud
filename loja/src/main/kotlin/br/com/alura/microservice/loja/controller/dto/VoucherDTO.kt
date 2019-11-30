package br.com.alura.microservice.loja.controller.dto

import java.time.LocalDate

class VoucherDTO(
	var numero: Long? = null,
	var previsaoParaEntrega: LocalDate? = null)