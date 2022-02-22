package br.com.nicolas.atentabrasil.data.response

data class Cep(
    val cep: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
    val service: String
)
