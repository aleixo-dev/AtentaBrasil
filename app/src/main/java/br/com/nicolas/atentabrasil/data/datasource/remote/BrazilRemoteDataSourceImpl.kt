package br.com.nicolas.atentabrasil.data.datasource.remote

import javax.inject.Inject

class BrazilRemoteDataSourceImpl @Inject constructor(
    private val api: BrazilApi
) : BrazilRemoteDataSource {

    override suspend fun fetchCep(cep: String) {
        api.fetchCep(cep)
    }
}