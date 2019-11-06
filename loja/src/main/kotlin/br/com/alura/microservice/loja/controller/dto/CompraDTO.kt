package br.com.alura.microservice.loja.controller.dto

data class CompraDTO(
	val itens: List<ItemDaCompraDTO>,
	val endereco: EnderecoDTO
)