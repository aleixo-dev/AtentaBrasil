package br.com.nicolas.atentabrasil.presentation.ddd

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.nicolas.atentabrasil.databinding.DirectDialingFragmentBinding
import br.com.nicolas.atentabrasil.domain.model.DddUiDomain
import br.com.nicolas.atentabrasil.presentation.ddd.adapter.DirectDialingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DirectDialingFragment : Fragment() {

    private val viewModel: DirectDialingViewModel by viewModels()

    private var _binding: DirectDialingFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DirectDialingFragmentBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerChangesInTextInput()
    }

    private fun observerChangesInTextInput() = binding.apply {
        inputDdd.editText?.addTextChangedListener { editable ->
            if (editable.isNullOrEmpty()) {
                flipperChildDdd.flipperDdd.displayedChild = FLIPPER_CHILD_IMAGE
            } else if (editable.length >= 2) {
                viewModel.fetchDdd(editable.toString())
                observerChangeViewModel()
            }
        }
    }

    private fun observerChangeViewModel() = lifecycleScope.launch {
        viewModel.uiState.collectLatest { state ->
            binding.apply {
                flipperChildDdd.flipperDdd.displayedChild = when (state) {
                    is DirectDialingViewModel.DirectState.Loading -> {
                        FLIPPER_CHILD_LOADING
                    }
                    is DirectDialingViewModel.DirectState.Success -> {
                        initRecyclerView(state.ddd)
                        FLIPPER_CHILD_DDD
                    }
                    is DirectDialingViewModel.DirectState.Error -> {
                        FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }

    private fun initRecyclerView(listDdd: List<DddUiDomain>) = binding.apply {
        with(this.flipperChildDdd.recyclerDdd) {
            setHasFixedSize(true)
            adapter = DirectDialingAdapter(listDdd)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_IMAGE = 0
        private const val FLIPPER_CHILD_LOADING = 1
        private const val FLIPPER_CHILD_DDD = 2
        private const val FLIPPER_CHILD_ERROR = 3
    }
}