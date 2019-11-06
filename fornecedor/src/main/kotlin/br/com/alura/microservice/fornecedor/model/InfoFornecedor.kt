package br.com.alura.microservice.fornecedor.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
class InfoFornecedor(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long,

	val nome: String,

	val estado: String,
	
	val endereco: String
)