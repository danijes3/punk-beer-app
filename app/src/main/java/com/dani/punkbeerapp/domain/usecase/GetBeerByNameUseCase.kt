package com.dani.punkbeerapp.domain.usecase

import com.dani.punkbeerapp.data.model.Beer
import com.dani.punkbeerapp.data.util.Resource
import com.dani.punkbeerapp.domain.repository.BeerRepository

class GetBeerByNameUseCase(private val beerRepository: BeerRepository) {
    suspend fun execute(page : Int, perPage : Int, beerName : String) : Resource<List<Beer>> {
        return beerRepository.getBeersByName(page, perPage, beerName)
    }
}