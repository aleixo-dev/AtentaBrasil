package br.com.nicolas.atentabrasil.common

sealed class Resource<out T> {
    data class Loading(val isLoading: Boolean) : Resource<Nothing>()
    data class Success<T>(val item: T) : Resource<T>()
    data class Error(val throwable: String) : Resource<Nothing>()
}
