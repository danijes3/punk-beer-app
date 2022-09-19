package com.dani.punkbeerapp.data.repository

import com.dani.punkbeerapp.data.api.BeersAPIService
import com.dani.punkbeerapp.data.repository.datasource.BeerRemoteDataSource
import com.dani.punkbeerapp.domain.repository.BeerRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before

import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BeerRepositoryImplTest {
    private lateinit var service : BeersAPIService
    private lateinit var mockServer : MockWebServer

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeersAPIService::class.java)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun fetch_beers_from_api() {
        // Given
        runBlocking {
            mockServer.enqueue(mockResponseFromFile("success_response.json"))
            val responseApi = service.getBeers(1, 20).body()
            val response = Response.success(responseApi!!.toList())

            val api = mockk<BeerRemoteDataSource> {
                coEvery {
                    getBeers(1, 20)
                } returns response
            }
            val repository: BeerRepository = BeerRepositoryImpl(api)

            // When
            repository.getBeers(1, 20)

            // Then
            coVerify { api.getBeers(1, 20) }
        }
    }

    @Test
    fun search_beers_from_api() {
        // Given
        runBlocking {
            mockServer.enqueue(mockResponseFromFile("success_response.json"))
            val responseApi = service.getBeersByName(1, 20, "Pilsen").body()
            val response = Response.success(responseApi!!.toList())

            val api = mockk<BeerRemoteDataSource> {
                coEvery {
                    getBeersByName(1, 20, "Pilsen")
                } returns response
            }
            val repository: BeerRepository = BeerRepositoryImpl(api)

            // When
            repository.getBeersByName(1, 20, "Pilsen")

            // Then
            coVerify { api.getBeersByName(1, 20, "Pilsen") }
        }
    }

    private fun mockResponseFromFile(file: String) : MockResponse {
        val input = javaClass.classLoader.getResourceAsStream(file)
        val builder = input.source().buffer()
        val mockServerResponse = MockResponse()
        mockServerResponse.setBody(builder.readString(Charsets.UTF_8))
        return mockServerResponse
    }
}