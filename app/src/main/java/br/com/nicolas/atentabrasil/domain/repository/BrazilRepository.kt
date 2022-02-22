package br.com.nicolas.atentabrasil.domain.repository

import br.com.nicolas.atentabrasil.data.response.Cep

interface BrazilRepository {

    suspend fun fetchCep(cep: String): Cep

}