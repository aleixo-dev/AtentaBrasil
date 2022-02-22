package br.com.nicolas.atentabrasil.presentation.ddd.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nicolas.atentabrasil.domain.model.DddUiDomain

class DirectDialingAdapter(
    private val listCities : List<DddUiDomain>
) : RecyclerView.Adapter<DirectDialingViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DirectDialingViewHolder {
        return DirectDialingViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: DirectDialingViewHolder,
        position: Int
    ) {
        holder.bind(listCities[position])
    }

    override fun getItemCount() = listCities.size


}