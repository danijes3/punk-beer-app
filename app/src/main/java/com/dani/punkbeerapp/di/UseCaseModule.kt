package com.dani.punkbeerapp.di

import com.dani.punkbeerapp.domain.repository.BeerRepository
import com.dani.punkbeerapp.domain.usecase.GetBeerByNameUseCase
import com.dani.punkbeerapp.domain.usecase.GetBeersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetBeerUseCase(beerRepository: BeerRepository) : GetBeersUseCase {
        return GetBeersUseCase(beerRepository)
    }

    @Singleton
    @Provides
    fun provideGetBeerByNameUseCase(beerRepository: BeerRepository) : GetBeerByNameUseCase {
        return GetBeerByNameUseCase(beerRepository)
    }
}