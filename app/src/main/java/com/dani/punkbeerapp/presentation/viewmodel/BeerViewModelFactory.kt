package com.dani.punkbeerapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dani.punkbeerapp.domain.usecase.GetBeerByNameUseCase
import com.dani.punkbeerapp.domain.usecase.GetBeersUseCase

class BeerViewModelFactory (
    private val app : Application,
    private val getBeersUseCase: GetBeersUseCase,
    private val getBeerByNameUseCase: GetBeerByNameUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeerViewModel(app, getBeersUseCase, getBeerByNameUseCase) as T
    }
}