package br.com.nicolas.atentabrasil.data.repository

import br.com.nicolas.atentabrasil.data.datasource.remote.BrazilApi
import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.domain.repository.BrazilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BrazilRepositoryImpl @Inject constructor(
    private val remote: BrazilApi
) : BrazilRepository {

    override suspend fun fetchCep(cep: String) = remote.fetchCep(cep)
}