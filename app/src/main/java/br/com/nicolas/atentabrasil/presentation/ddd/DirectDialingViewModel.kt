package br.com.nicolas.atentabrasil.presentation.ddd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.atentabrasil.common.DataState
import br.com.nicolas.atentabrasil.common.Resource
import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.domain.model.DddUiDomain
import br.com.nicolas.atentabrasil.domain.usecase.FetchDddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectDialingViewModel @Inject constructor(
    private val fetchDddUseCase: FetchDddUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DirectState>(
        DirectState.Loading()
    )
    val uiState: SharedFlow<DirectState> get() = _uiState

    fun fetchDdd(ddd: String) = viewModelScope.launch {
        fetchDddUseCase.invoke(ddd).collect { state ->
            when (state) {
                is Resource.Loading -> {
                    _uiState.value = DirectState.Loading(true)
                }
                is Resource.Success -> {
                    _uiState.value = DirectState.Success(state.item)
                }
                is Resource.Error -> {
                    _uiState.value = DirectState.Error(
                        state.throwable
                    )
                }
            }
        }
    }

    sealed class DirectState {
        data class Loading(val isLoading: Boolean = true) : DirectState()
        data class Success(val ddd: List<DddUiDomain>) : DirectState()
        data class Error(val error: String) : DirectState()
    }
}