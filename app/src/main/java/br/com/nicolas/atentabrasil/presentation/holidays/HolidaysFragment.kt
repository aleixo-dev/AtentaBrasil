package br.com.nicolas.atentabrasil.presentation.holidays

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.nicolas.atentabrasil.R

class HolidaysFragment : Fragment() {

    companion object {
        fun newInstance() = HolidaysFragment()
    }

    private lateinit var viewModel: HolidaysViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.holidays_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HolidaysViewModel::class.java)
    }
}