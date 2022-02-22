package br.com.nicolas.atentabrasil.presentation.cep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.atentabrasil.common.DataState
import br.com.nicolas.atentabrasil.common.Resource
import br.com.nicolas.atentabrasil.domain.usecase.FetchCepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CepViewModel @Inject constructor(
    private val fetchCepUseCase: FetchCepUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DataState>(
        DataState.Loading()
    )
    val uiState: StateFlow<DataState> = _uiState

    fun fetchCep(cep: String) {
        viewModelScope.launch {
            fetchCepUseCase.invoke(cep).collect {
                when (it) {
                    is Resource.Loading -> {
                        _uiState.value = DataState.Loading(true)
                    }
                    is Resource.Success -> {
                        _uiState.value = DataState.Success(it.item)
                    }
                    is Resource.Error -> {
                        _uiState.value = DataState.Error(
                            it.throwable ?: "Cep não encontrado"
                        )
                    }
                }
            }
        }
    }
}