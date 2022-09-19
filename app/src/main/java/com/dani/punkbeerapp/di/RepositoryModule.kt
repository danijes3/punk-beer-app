package com.dani.punkbeerapp.di

import com.dani.punkbeerapp.data.repository.BeerRepositoryImpl
import com.dani.punkbeerapp.data.repository.datasource.BeerRemoteDataSource
import com.dani.punkbeerapp.domain.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideBeerRepository(
        beerRemoteDataSource: BeerRemoteDataSource
    ) : BeerRepository {
        return BeerRepositoryImpl(
            beerRemoteDataSource
        )
    }
}