package com.dani.punkbeerapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.dani.punkbeerapp.data.model.Beer
import com.dani.punkbeerapp.data.util.Resource
import com.dani.punkbeerapp.domain.usecase.GetBeerByNameUseCase
import com.dani.punkbeerapp.domain.usecase.GetBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val app : Application,
    private val getBeersUseCase: GetBeersUseCase,
    private val getBeerByNameUseCase: GetBeerByNameUseCase
) : AndroidViewModel(app){

    val beerList : MutableLiveData<Resource<List<Beer>>> = MutableLiveData()

    fun getBeers(page : Int, perPage : Int) = viewModelScope.launch(Dispatchers.IO) {
        beerList.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)) {
                val result = getBeersUseCase.execute(page, perPage)
                beerList.postValue(result)
            } else {
                beerList.postValue((Resource.Error("No Internet connection")))
            }
        }catch (e : Exception){
            beerList.postValue(Resource.Error(e.message.toString()))
        }
    }

    val beerListByName : MutableLiveData<Resource<List<Beer>>> = MutableLiveData()

    fun getBeersByName(page : Int, perPage : Int, beerName : String) {
        viewModelScope.launch(Dispatchers.IO){
            beerListByName.postValue(Resource.Loading())
            try {
                if (isInternetAvailable(app)) {
                    val searchName = beerName.replace(" ", "_")
                    val result = getBeerByNameUseCase.execute(page, perPage, searchName)
                    beerListByName.postValue(result)
                } else {
                    beerListByName.postValue((Resource.Error("No Internet connection")))
                }
            }catch (e : Exception){
                beerListByName.postValue(Resource.Error(e.message.toString()))
            }

        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo ?: return false
            return activeNetwork.isConnectedOrConnecting
        }
    }
}