package br.com.nicolas.atentabrasil.di

import br.com.nicolas.atentabrasil.data.datasource.remote.BrazilRemoteDataSource
import br.com.nicolas.atentabrasil.data.datasource.remote.BrazilRemoteDataSourceImpl
import br.com.nicolas.atentabrasil.data.repository.BrazilRepositoryImpl
import br.com.nicolas.atentabrasil.domain.repository.BrazilRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBrazilRepository(
        repository: BrazilRepositoryImpl
    ): BrazilRepository

    @Binds
    abstract fun bindBrazilRemoteDataSource(
        repository: BrazilRemoteDataSourceImpl
    ): BrazilRemoteDataSource
}