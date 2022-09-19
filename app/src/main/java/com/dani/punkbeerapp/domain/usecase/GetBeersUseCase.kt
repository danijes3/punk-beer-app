package com.dani.punkbeerapp.domain.usecase

import com.dani.punkbeerapp.data.model.Beer
import com.dani.punkbeerapp.data.util.Resource
import com.dani.punkbeerapp.domain.repository.BeerRepository

class GetBeersUseCase(private val beerRepository: BeerRepository) {
    suspend fun execute(page : Int, perPage : Int) :  Resource<List<Beer>> {
        return beerRepository.getBeers(page, perPage)
    }
}