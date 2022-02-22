package br.com.nicolas.atentabrasil.data.response

import br.com.nicolas.atentabrasil.domain.model.DddUiDomain

data class DirectDialing(
    val cities: List<String>,
    val state: String
)

fun DirectDialing.toUiDomain(cities: List<String>) : ArrayList<DddUiDomain>{

    val dddList = ArrayList<DddUiDomain>()

    for(i in cities){
        val data = DddUiDomain(
            name = i
        )
        dddList.add(data)
    }
    return dddList
}