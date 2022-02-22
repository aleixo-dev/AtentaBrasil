package br.com.nicolas.atentabrasil.common

import br.com.nicolas.atentabrasil.data.response.Cep

sealed class CepState {
    data class Loading(val isLoading: Boolean = true) : CepState()
    data class Success(val cep: Cep) : CepState()
    data class Error(val error: String) : CepState()
}
