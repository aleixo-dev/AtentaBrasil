package br.com.nicolas.atentabrasil.presentation.cep

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.nicolas.atentabrasil.common.DataState
import br.com.nicolas.atentabrasil.databinding.CepFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CepFragment : Fragment() {

    private val viewModel: CepViewModel by viewModels()

    private var _binding: CepFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CepFragmentBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerTextChanged()
    }

    private fun observerTextChanged() {
        binding.inputCep.editText?.addTextChangedListener { editable ->
            if (editable.isNullOrEmpty()) {
                binding.includeFlipper.flipperCep.displayedChild = FLIPPER_CHILD_IMAGE
            } else if (editable.length >= LENGTH_CHAR) {
                viewModel.fetchCep(editable.toString())
                observeChangesInViewModel()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeChangesInViewModel() {
        lifecycleScope.launch {
            viewModel.uiState.observe(viewLifecycleOwner) {
                binding.includeFlipper.flipperCep.displayedChild = when (it) {
                    is DataState.Loading -> {
                        FLIPPER_CHILD_LOADING
                    }
                    is DataState.Success -> {
                        binding.apply {
                            it.data.run {
                                includeFlipper.textCep.text =
                                    "Cep: ${this.cep}\n" +
                                            "Estado: ${this.state}\n" +
                                            "Cidade: ${this.city}\n" +
                                            "Bairro: ${this.neighborhood}\n" +
                                            "Rua: ${this.street}\n"
                            }
                        }
                        FLIPPER_CHILD_CEP
                    }
                    is DataState.Error -> {
                        FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_IMAGE = 0
        private const val FLIPPER_CHILD_LOADING = 1
        private const val FLIPPER_CHILD_CEP = 2
        private const val FLIPPER_CHILD_ERROR = 3

        private const val LENGTH_CHAR = 8

    }
}