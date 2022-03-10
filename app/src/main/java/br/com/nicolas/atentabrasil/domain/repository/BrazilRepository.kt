package br.com.nicolas.atentabrasil.domain.repository

import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.data.response.HolidaysItem
import br.com.nicolas.atentabrasil.domain.model.DddUiDomain

interface BrazilRepository {

    suspend fun fetchCep(cep: String): Cep

    suspend fun fetchDdd(ddd: String): List<DddUiDomain>

    suspend fun fetchDateHolidays(year: Int): List<HolidaysItem>
}