package com.dani.punkbeerapp.data.api

import com.dani.punkbeerapp.data.model.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersAPIService {
    @GET("v2/beers")
    suspend fun getBeers(
        @Query("page")
        page : Int,
        @Query("per_page")
        perPage: Int
    ) : Response<List<Beer>>

    @GET("v2/beers")
    suspend fun getBeersByName(
        @Query("page")
        page : Int,
        @Query("per_page")
        perPage: Int,
        @Query("beer_name")
        beerName: String
    ) : Response<List<Beer>>
}