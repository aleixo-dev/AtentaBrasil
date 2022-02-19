package br.com.nicolas.atentabrasil.presentation.ddd

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.nicolas.atentabrasil.R

class DirectDialingFragment : Fragment() {

    companion object {
        fun newInstance() = DirectDialingFragment()
    }

    private lateinit var viewModel: DirectDialingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.direct_dialing_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[DirectDialingViewModel::class.java]
        // TODO: Use the ViewModel
    }

}