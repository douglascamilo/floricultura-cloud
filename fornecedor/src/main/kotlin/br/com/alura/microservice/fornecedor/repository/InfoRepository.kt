package br.com.alura.microservice.fornecedor.repository

import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import br.com.alura.microservice.fornecedor.model.InfoFornecedor

@Repository
interface InfoRepository: CrudRepository<InfoFornecedor, Long> {

	fun findByEstado(estado: String): InfoFornecedor
}