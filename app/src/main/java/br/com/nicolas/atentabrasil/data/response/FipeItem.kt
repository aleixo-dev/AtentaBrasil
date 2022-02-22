package br.com.nicolas.atentabrasil.data.response

data class FipeItem(
    val anoModelo: Int,
    val codigoFipe: String,
    val combustivel: String,
    val dataConsulta: String,
    val marca: String,
    val mesReferencia: String,
    val modelo: String,
    val siglaCombustivel: String,
    val tipoVeiculo: Int,
    val valor: String
)