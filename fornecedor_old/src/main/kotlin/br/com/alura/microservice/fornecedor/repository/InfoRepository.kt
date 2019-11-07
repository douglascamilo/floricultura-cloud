package br.com.alura.microservice.fornecedor.repository

import br.com.alura.microservice.fornecedor.model.InfoFornecedor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InfoRepository: CrudRepository<InfoFornecedor, Long> {

	fun findByEstado(estado: String): InfoFornecedor
}