package br.com.nicolas.atentabrasil.data.repository

import br.com.nicolas.atentabrasil.data.datasource.remote.BrazilRemoteDataSource
import br.com.nicolas.atentabrasil.data.response.toUiDomain
import br.com.nicolas.atentabrasil.domain.model.DddUiDomain
import br.com.nicolas.atentabrasil.domain.repository.BrazilRepository
import javax.inject.Inject

class BrazilRepositoryImpl @Inject constructor(
    private val remote: BrazilRemoteDataSource
) : BrazilRepository {

    override suspend fun fetchCep(cep: String) = remote.fetchCep(cep)

    override suspend fun fetchDdd(ddd: String): List<DddUiDomain> {
        val response = remote.fetchDDD(ddd)
        return response.toUiDomain(response.cities)
    }
}