package br.com.nicolas.atentabrasil.common

sealed class DataState<T> {
    class Loading<T> : DataState<T>(){
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }
    data class Success<T>(val data : T) : DataState<T>()
    data class Error<T>(val error: String) : DataState<T>()
}
