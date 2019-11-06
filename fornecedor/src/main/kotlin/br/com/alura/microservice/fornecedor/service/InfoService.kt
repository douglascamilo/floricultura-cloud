package br.com.alura.microservice.fornecedor.service

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import br.com.alura.microservice.fornecedor.repository.InfoRepository
import br.com.alura.microservice.fornecedor.model.InfoFornecedor

@Service
class InfoService {
	@Autowired lateinit var infoRepository: InfoRepository

	fun getInfoPor(estado: String): InfoFornecedor {
		return infoRepository.findByEstado(estado)
	}
}