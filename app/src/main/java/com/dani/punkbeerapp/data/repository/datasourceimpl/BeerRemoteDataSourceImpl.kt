package com.dani.punkbeerapp.data.repository.datasourceimpl

import com.dani.punkbeerapp.data.api.BeersAPIService
import com.dani.punkbeerapp.data.model.Beer
import com.dani.punkbeerapp.data.repository.datasource.BeerRemoteDataSource
import retrofit2.Response

class BeerRemoteDataSourceImpl(
    private val beersAPIService : BeersAPIService
) : BeerRemoteDataSource {

    override suspend fun getBeers(page: Int, perPage: Int) : Response<List<Beer>> {
        return beersAPIService.getBeers(page, perPage)
    }

    override suspend fun getBeersByName(
        page : Int,
        perPage : Int,
        beerName: String
    ): Response<List<Beer>> {
        return beersAPIService.getBeersByName(page, perPage, beerName)
    }
}