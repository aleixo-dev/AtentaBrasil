package br.com.nicolas.atentabrasil.domain.usecase

import br.com.nicolas.atentabrasil.common.Resource
import br.com.nicolas.atentabrasil.data.response.DirectDialing
import br.com.nicolas.atentabrasil.domain.model.DddUiDomain
import br.com.nicolas.atentabrasil.domain.repository.BrazilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchDddUseCase @Inject constructor(
    private val repository: BrazilRepository
) {
    operator fun invoke(ddd: String): Flow<Resource<List<DddUiDomain>>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = repository.fetchDdd(ddd)
            emit(Resource.Success(response))
        } catch (exception: HttpException) {
            emit(Resource.Error(exception.localizedMessage ?: "An unexpected error occured"))
        } catch (exception: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}