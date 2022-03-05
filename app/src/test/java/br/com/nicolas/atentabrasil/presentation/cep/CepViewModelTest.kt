package br.com.nicolas.atentabrasil.presentation.cep

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.nicolas.atentabrasil.MainCoroutineRule
import br.com.nicolas.atentabrasil.common.DataState
import br.com.nicolas.atentabrasil.common.Resource
import br.com.nicolas.atentabrasil.data.response.Cep
import br.com.nicolas.atentabrasil.domain.usecase.FetchCepUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(JUnit4::class)
class CepViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val fetchCepUseCase = mock<FetchCepUseCase>()

    private lateinit var cepViewModel: CepViewModel

    @Before
    fun setUp() {
        cepViewModel = CepViewModel(fetchCepUseCase)
    }

    private val fakeCepData = Cep(
        "89010025",
        "SC",
        "Blumenau",
        "Centro",
        "Rua Doutor Luiz de Freitas Melro",
        "viacep",
    )

    @ExperimentalCoroutinesApi
    @Test
    fun `when fetch use case is calling return successful`() = runTest {
        val loading = DataState.Loading(true)
        val success = DataState.Success(fakeCepData)
        val collect = mutableListOf<DataState>()

        cepViewModel.uiState.observeForever {
            collect.add(it)
        }

        whenever(
            fetchCepUseCase.invoke("07600415")
        ).thenReturn(
            flowOf(
                Resource.Success(fakeCepData)
            )
        )

        cepViewModel.fetchCep("07600415")

        assertEquals(loading, collect[0])
        assertEquals(success, collect[1])
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `when fetch use case is calling return error`() = runTest {

        val twoLoading = DataState.Loading(true)
        val error = DataState.Error("")
        val collection = mutableListOf<DataState>()

        cepViewModel.uiState.observeForever {
            collection.add(it)
        }

        whenever(fetchCepUseCase.invoke(any()))
            .thenReturn(
                flowOf(
                    Resource.Error("")
                )
            )

        cepViewModel.fetchCep("")

        assertEquals(twoLoading, collection[0])
        assertEquals(error, collection[1])
    }
}