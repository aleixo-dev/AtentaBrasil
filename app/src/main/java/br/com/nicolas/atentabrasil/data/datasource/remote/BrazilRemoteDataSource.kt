package br.com.nicolas.atentabrasil.data.datasource.remote

interface BrazilRemoteDataSource {

    suspend fun fetchCep(cep : String)

}