package com.dani.punkbeerapp.di

import com.dani.punkbeerapp.presentation.RecyclerViewAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class RecyclerViewAdapterModule {
    @Singleton
    @Provides
    fun provideRecyclerViewAdapter() : RecyclerViewAdapter {
        return RecyclerViewAdapter()
    }
}