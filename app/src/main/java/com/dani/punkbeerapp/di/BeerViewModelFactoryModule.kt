package com.dani.punkbeerapp.di

import android.app.Application
import com.dani.punkbeerapp.domain.usecase.GetBeerByNameUseCase
import com.dani.punkbeerapp.domain.usecase.GetBeersUseCase
import com.dani.punkbeerapp.presentation.viewmodel.BeerViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BeerViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideBeerViewModelFactory(
        app : Application,
        getBeersUseCase: GetBeersUseCase,
        getBeerByNameUseCase: GetBeerByNameUseCase
    ) : BeerViewModelFactory{
        return BeerViewModelFactory(app, getBeersUseCase, getBeerByNameUseCase)
    }
}