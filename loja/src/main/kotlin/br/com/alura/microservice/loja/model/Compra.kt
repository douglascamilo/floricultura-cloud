package br.com.alura.microservice.loja.model

import javax.persistence.Entity
import javax.persistence.Id
import java.time.LocalDate

@Entity
class Compra(
	@Id var pedidoId: Long? = null,
	var tempoDePreparo: Int? = null,
	var enderecoDestino: String? = null,
	var dataParaEntrega: LocalDate? = null,
	var voucher: Long? = null
)