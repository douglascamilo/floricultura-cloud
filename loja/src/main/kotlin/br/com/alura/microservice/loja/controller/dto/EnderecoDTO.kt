package br.com.alura.microservice.loja.controller.dto

class EnderecoDTO(
	val rua: String,
	val numero: Int,
	val estado: String) {

	override fun toString(): String {
		return "${rua}, ${numero} - ${estado}"
	}
}