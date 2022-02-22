package br.com.nicolas.atentabrasil.presentation.ddd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nicolas.atentabrasil.databinding.LayoutItemDddBinding
import br.com.nicolas.atentabrasil.domain.model.DddUiDomain

class DirectDialingViewHolder(
    layoutItemDddBinding: LayoutItemDddBinding
) : RecyclerView.ViewHolder(layoutItemDddBinding.root) {

    private val textCities = layoutItemDddBinding.textNameCities

    fun bind(ddd: DddUiDomain) {
        textCities.text = ddd.name
    }

    companion object {
        fun create(parent: ViewGroup): DirectDialingViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = LayoutItemDddBinding.inflate(
                inflater, parent, false
            )
            return DirectDialingViewHolder(itemBinding)
        }
    }
}
