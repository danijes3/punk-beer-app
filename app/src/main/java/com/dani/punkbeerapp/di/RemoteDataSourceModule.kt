package com.dani.punkbeerapp.di

import com.dani.punkbeerapp.data.api.BeersAPIService
import com.dani.punkbeerapp.data.repository.datasource.BeerRemoteDataSource
import com.dani.punkbeerapp.data.repository.datasourceimpl.BeerRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideBeerRemoteDataSource(beersAPIService: BeersAPIService) : BeerRemoteDataSource {
        return BeerRemoteDataSourceImpl(beersAPIService)
    }
}

