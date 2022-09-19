package com.dani.punkbeerapp.di

import com.dani.punkbeerapp.BuildConfig
import com.dani.punkbeerapp.data.api.BeersAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideBeersAPIService(retrofit: Retrofit) : BeersAPIService {
        return retrofit.create(BeersAPIService::class.java)
    }
}