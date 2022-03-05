package br.com.nicolas.atentabrasil.domain.usecase

import br.com.nicolas.atentabrasil.common.Resource
import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.domain.repository.BrazilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface FetchCepUseCase {
    operator fun invoke(cep: String): Flow<Resource<Cep>>
}

class FetchCepUseCaseImpl @Inject constructor(
    private val repository: BrazilRepository
) : FetchCepUseCase {
    override operator fun invoke(cep: String): Flow<Resource<Cep>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = repository.fetchCep(cep)
            emit(Resource.Success(response))
        } catch (exception: HttpException) {
            emit(Resource.Error(exception.localizedMessage ?: "An unexpected error occured"))
        } catch (exception: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}