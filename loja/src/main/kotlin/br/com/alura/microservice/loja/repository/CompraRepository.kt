package br.com.alura.microservice.loja.repository

import org.springframework.data.repository.CrudRepository
import br.com.alura.microservice.loja.model.Compra
import org.springframework.stereotype.Repository

interface CompraRepository: CrudRepository<Compra, Long>