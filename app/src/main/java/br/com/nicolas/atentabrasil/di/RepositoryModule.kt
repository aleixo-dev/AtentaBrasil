package br.com.nicolas.atentabrasil.di

import br.com.nicolas.atentabrasil.data.repository.BrazilRepositoryImpl
import br.com.nicolas.atentabrasil.domain.repository.BrazilRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindBrazilRepository(repository: BrazilRepositoryImpl): BrazilRepository
}