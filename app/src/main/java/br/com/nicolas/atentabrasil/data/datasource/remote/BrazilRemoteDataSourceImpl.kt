package br.com.nicolas.atentabrasil.data.datasource.remote

import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.data.response.DirectDialing
import br.com.nicolas.atentabrasil.data.response.Holidays
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

    override suspend fun fetchDateHolidays(year: Int) = api.fetchHolidays(year)
}