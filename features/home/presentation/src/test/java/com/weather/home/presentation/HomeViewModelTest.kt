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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

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
        viewModel = HomeViewModel(getCurrentWeatherUseCase, getCityUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun `getCityName should update cityName in state`() = runTest {
        val cityName = "Amman"
        val cityFlow = flowOf(cityName)
        val fakeWeather = mockk<WeatherModel>()

        coEvery { getCityUseCase() } returns cityFlow
        coEvery { getCurrentWeatherUseCase(WeatherRequest(cityName)) } returns fakeWeather

        viewModel.homeState.test {
            val initialState = awaitItem()
            assertTrue(initialState.isLoading)
            assertNull(initialState.cityName)

            val cityState = awaitItem()
            assertEquals(cityName, cityState.cityName)

            val weatherState = awaitItem()
            assertEquals(fakeWeather, weatherState.weather)
            assertFalse(weatherState.isLoading)
        }
    }

    @Test
    fun `getWeather failure should update errorMessage and stop loading`() = runTest {
        val cityName = "Amman"
        val errorMessage = "Network Error"

        coEvery { getCityUseCase() } returns flowOf(cityName)
        coEvery { getCurrentWeatherUseCase(any()) } throws Exception(errorMessage)


        viewModel.homeState.test {
            val initialState = awaitItem()
            assertTrue(initialState.isLoading)
            assertNull(initialState.cityName)

            val cityState = awaitItem()
            assertEquals(cityName, cityState.cityName)


            val errorState = awaitItem()
            assertEquals(errorMessage, errorState.errorMessage)
            assertFalse(errorState.isLoading)
            assertNull(errorState.weather)
        }
    }

    @Test
    fun `getCityName with empty city should not fetch weather`() = runTest {
        val cityFlow = flowOf("")
        coEvery { getCityUseCase() } returns cityFlow

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            val initialState = awaitItem()
            assertTrue(initialState.isLoading)
            assertNull(initialState.cityName)

            val cityState = awaitItem()
            assertEquals("", cityState.cityName)

        }
    }
}

