package com.dani.punkbeerapp.data.repository.datasource

import com.dani.punkbeerapp.data.model.Beer
import retrofit2.Response

interface BeerRemoteDataSource {
    suspend fun getBeers(page: Int, perPage: Int) : Response<List<Beer>>
    suspend fun getBeersByName(page : Int, perPage : Int, beerName: String) : Response<List<Beer>>
}