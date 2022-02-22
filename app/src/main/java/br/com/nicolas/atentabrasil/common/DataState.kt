package br.com.nicolas.atentabrasil.common

import br.com.nicolas.atentabrasil.data.response.Cep

sealed class DataState {
    data class Loading(val isLoading: Boolean = true) : DataState()
    data class Success(val cep: Cep) : DataState()
    data class Error(val error: String) : DataState()
}
