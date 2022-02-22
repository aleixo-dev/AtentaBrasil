package br.com.nicolas.atentabrasil.data.datasource.remote

import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.data.response.DirectDialing
import javax.inject.Inject

class BrazilRemoteDataSourceImpl @Inject constructor(
    private val api: BrazilApi
) : BrazilRemoteDataSource {

    override suspend fun fetchCep(cep: String): Cep {
        return api.fetchCep(cep)
    }

    override suspend fun fetchDDD(ddd: String): DirectDialing {
        return api.fetchDDD(ddd)
    }
}