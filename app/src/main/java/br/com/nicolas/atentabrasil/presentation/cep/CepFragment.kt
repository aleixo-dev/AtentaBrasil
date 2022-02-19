package br.com.nicolas.atentabrasil.presentation.cep

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.nicolas.atentabrasil.R

class CepFragment : Fragment() {

    companion object {
        fun newInstance() = CepFragment()
    }

    private lateinit var viewModel: CepViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cep_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CepViewModel::class.java)
        // TODO: Use the ViewModel
    }

}