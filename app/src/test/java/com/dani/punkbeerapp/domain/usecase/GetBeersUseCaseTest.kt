package com.dani.punkbeerapp.domain.usecase

import com.dani.punkbeerapp.domain.repository.BeerRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetBeersUseCaseTest {

    @Test
    fun getBeersUseCase_execute_check_from_repository() {
        runBlocking {
            // Given
            val repository = mockk<BeerRepository>(relaxed = true)
            val useCase = GetBeersUseCase(repository)

            // When
            useCase.execute(1, 20)

            // Then
            coVerify { repository.getBeers(1, 20) }
        }
    }
}