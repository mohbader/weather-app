package com.weather.home.presentation

import app.cash.turbine.test
import com.weather.datastore.usecases.GetCityUseCase
import com.weather.home.domain.model.WeatherModel
import com.weather.home.domain.model.WeatherRequest
import com.weather.home.domain.usecase.GetCurrentWeatherUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @MockK
    lateinit var getCityUseCase: GetCityUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getCityName should update cityName in state`() = runTest {
        val cityFlow = flowOf("Amman")
        val fakeWeather = mockk<WeatherModel>()
        coEvery { getCityUseCase() } returns cityFlow

        coEvery {
            getCurrentWeatherUseCase(WeatherRequest("Amman"))
        } returns fakeWeather

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {

            skipItems(3)

            val weatherResult = awaitItem()
            assertEquals(fakeWeather, weatherResult.weather)
            assertFalse(weatherResult.isLoading)
        }
    }

    @Test
    fun `getWeather failure should update errorMessage and stop loading`() = runTest {
        val cityName = "Amman"
        val errorMessage = "Network Error"

        coEvery { getCityUseCase() } returns flowOf(cityName)
        coEvery { getCurrentWeatherUseCase(any()) } throws Exception(errorMessage)

        viewModel = HomeViewModel(getCurrentWeatherUseCase, getCityUseCase)

        viewModel.homeState.test {

            skipItems(3)
            val errorState = awaitItem()
            assertEquals(errorMessage, errorState.errorMessage)
            assertFalse(errorState.isLoading)
            assertNull(errorState.weather)
        }
    }

    @Test
    fun `loading is true while fetching weather and false when done`() = runTest {

        coEvery { getCityUseCase() } returns flowOf("Amman")
        coEvery { getCurrentWeatherUseCase(WeatherRequest("Amman")) } returns mockk()

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            awaitItem()
            assertTrue(awaitItem().isLoading)
            skipItems(1)
            assertFalse(awaitItem().isLoading)
        }
    }
}