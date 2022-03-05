package br.com.nicolas.atentabrasil.di

import br.com.nicolas.atentabrasil.domain.usecase.FetchCepUseCase
import br.com.nicolas.atentabrasil.domain.usecase.FetchCepUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindFetchCepUseCase(useCase: FetchCepUseCaseImpl): FetchCepUseCase
}