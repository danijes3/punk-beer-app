package com.dani.punkbeerapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BeersAPIServiceTest {
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
    fun getBeers_response_not_null() {
        runBlocking {
            mockServer.enqueue(mockResponseFromFile("success_response.json"))
            val response = service.getBeers(1,20).body()
            assertThat(response).isNotNull()
        }
    }

    @Test
    fun getBeers_check_request_path() {
        runBlocking {
            mockServer.enqueue(mockResponseFromFile("success_response.json"))
            val request = mockServer.takeRequest()
            assertThat(request.path).isEqualTo("/v2/beers?page=1&per_page=20")
        }
    }

    @Test
    fun getBeers_check_list_size() {
        runBlocking {
            mockServer.enqueue(mockResponseFromFile("success_response.json"))
            val response = service.getBeers(1,20).body()
            val beers = response!!.toList()
            assertThat(beers.size).isEqualTo(20)
        }
    }

    @Test
    fun getBeers_check_item() {
        runBlocking {
            mockServer.enqueue(mockResponseFromFile("success_response.json"))
            val response = service.getBeers(1,20).body()
            val beers = response!!.toList()
            assertThat(beers[1].id).isEqualTo(2)
            assertThat(beers[1].name).isEqualTo("Trashy Blonde")
            assertThat(beers[1].abv).isEqualTo(4.1)
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