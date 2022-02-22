package br.com.nicolas.atentabrasil.data.datasource.remote

import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.data.response.DirectDialing
import br.com.nicolas.atentabrasil.data.response.Fipe
import br.com.nicolas.atentabrasil.data.response.Holidays
import br.com.nicolas.atentabrasil.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface BrazilApi {

    @GET(Constants.CEP_ENDPOINT)
    suspend fun fetchCep(
        @Path("cep") cep: String
    ): Cep

    @GET(Constants.DDD_ENDPOINT)
    suspend fun fetchDDD(
        @Path("ddd") ddd: Int
    ): DirectDialing

    @GET(Constants.HOLIDAYS_ENDPOINT)
    suspend fun fetchHolidays(
        @Path("ano") year: Int
    ): Holidays

    @GET(Constants.FIPE_ENDPOINT_CODE)
    suspend fun fetchCodeFipe(
        @Path("codigoFipe") codeFipe: String
    ): Fipe
}