package br.com.nicolas.atentabrasil.data.datasource.remote

import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.data.response.DirectDialing
import br.com.nicolas.atentabrasil.data.response.Holidays

interface BrazilRemoteDataSource {

    suspend fun fetchCep(cep: String): Cep

    suspend fun fetchDDD(ddd: String): DirectDialing

    suspend fun fetchDateHolidays(year: Int): Holidays

}