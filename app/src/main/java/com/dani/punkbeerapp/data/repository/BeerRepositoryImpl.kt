package com.dani.punkbeerapp.data.repository

import com.dani.punkbeerapp.data.model.Beer
import com.dani.punkbeerapp.data.repository.datasource.BeerRemoteDataSource
import com.dani.punkbeerapp.data.util.Resource
import com.dani.punkbeerapp.domain.repository.BeerRepository
import retrofit2.Response

class BeerRepositoryImpl(
    private val beerRemoteDataSource : BeerRemoteDataSource
) :BeerRepository {
    override suspend fun getBeers(page : Int, perPage : Int): Resource<List<Beer>> {
        return checkResponse(beerRemoteDataSource.getBeers(page, perPage))
    }

    override suspend fun getBeersByName(page : Int, perPage : Int,beerName: String): Resource<List<Beer>> {
        return checkResponse(beerRemoteDataSource.getBeersByName(page, perPage, beerName))
    }

    private fun checkResponse(response: Response<List<Beer>>) : Resource<List<Beer>> {
        if(response.isSuccessful) {
            response.body()?.let {  result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}