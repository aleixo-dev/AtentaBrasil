package br.com.nicolas.atentabrasil.presentation.cep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.atentabrasil.common.DataState
import br.com.nicolas.atentabrasil.common.Resource
import br.com.nicolas.atentabrasil.domain.usecase.FetchCepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CepViewModel @Inject constructor(
    private val fetchCepUseCase: FetchCepUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<DataState>(
        DataState.Loading()
    )
    val uiState: LiveData<DataState> = _uiState

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