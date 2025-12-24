package com.weather.presentation

import app.cash.turbine.test
import com.weather.datastore.usecases.SaveCityUseCase
import com.weather.domain.model.CityModel
import com.weather.domain.usecase.SearchCityUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {
    private val saveCityUseCase: SaveCityUseCase = mockk()
    private val searchCityUseCase: SearchCityUseCase = mockk()

    private lateinit var viewModel: SearchViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel(saveCityUseCase, searchCityUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchCity should update searchState with results from use case`() = runTest {
        val cityName = "Amman"
        val mockResults = listOf(CityModel(areaName = "Amman", country = "Jordan"))

        coEvery { searchCityUseCase(any()) } returns mockResults

        viewModel.searchCity(cityName)
        advanceUntilIdle()

        viewModel.searchState.test {
            val result = awaitItem()
            assertEquals(mockResults, result)
        }
    }

    @Test
    fun `searchCity does not emit when use case throws`() = runTest {
        coEvery {
            searchCityUseCase(any())
        } throws RuntimeException("Boom")

        viewModel.searchCity("Nowhere")

        viewModel.searchState.test {
            assertEquals(null, awaitItem())
        }
    }

    @Test
    fun `saveCity should call saveCityUseCase once`() = runTest {
        val cityName = "New York"
        coEvery { saveCityUseCase(cityName) } returns Unit

        viewModel.saveCity(cityName)
        advanceUntilIdle()

        coVerify(exactly = 1) { saveCityUseCase(cityName) }
    }

}