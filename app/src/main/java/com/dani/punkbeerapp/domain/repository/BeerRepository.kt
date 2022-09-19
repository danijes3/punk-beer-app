package com.dani.punkbeerapp.domain.repository

import com.dani.punkbeerapp.data.model.Beer
import com.dani.punkbeerapp.data.util.Resource

interface BeerRepository {

    suspend fun getBeers(page : Int, perPage : Int) : Resource<List<Beer>>
    suspend fun getBeersByName(page : Int, perPage : Int, beerName: String) : Resource<List<Beer>>
}