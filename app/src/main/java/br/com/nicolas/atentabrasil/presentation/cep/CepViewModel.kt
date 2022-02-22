package br.com.nicolas.atentabrasil.presentation.cep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.atentabrasil.common.CepState
import br.com.nicolas.atentabrasil.common.Resource
import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.domain.usecase.FetchCepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Error
import javax.inject.Inject

@HiltViewModel
class CepViewModel @Inject constructor(
    private val fetchCepUseCase: FetchCepUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CepState>(
        CepState.Loading()
    )
    val uiState: StateFlow<CepState> = _uiState

    fun fetchCep(cep: String) {
        viewModelScope.launch {
            fetchCepUseCase.invoke(cep).collect {
                when (it) {
                    is Resource.Loading -> {
                        _uiState.value = CepState.Loading(false)
                    }
                    is Resource.Success -> {
                        _uiState.value = CepState.Success(it.item)
                    }
                    is Resource.Error -> {
                        _uiState.value = CepState.Error(
                            it.throwable ?: "Cep não encontrado"
                        )
                    }
                }
            }
        }
    }
}