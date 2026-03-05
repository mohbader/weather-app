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

    @Test
    fun `getCityName with null city should not call getCurrentWeatherUseCase`() = runTest {
        val cityFlow = flowOf(null)
        coEvery { getCityUseCase() } returns cityFlow

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            val initialState = awaitItem()
            assertEquals(false, initialState.isLoading)
            assertNull(initialState.cityName)

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            val cityState = awaitItem()
            assertNull(cityState.cityName)
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
            awaitItem() // initial state
            awaitItem() // loading state
            val cityState = awaitItem()
            assertEquals("", cityState.cityName)
            assertNull(cityState.weather)
        }
    }

    @Test
    fun `getCityName with blank city should not fetch weather`() = runTest {
        val cityFlow = flowOf("   ")
        coEvery { getCityUseCase() } returns cityFlow

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            awaitItem() // initial state
            awaitItem() // loading state
            val cityState = awaitItem()
            assertEquals("   ", cityState.cityName)
            assertNull(cityState.weather)
        }
    }

    @Test
    fun `successful weather fetch should clear error message`() = runTest {
        val cityName = "London"
        val fakeWeather = mockk<WeatherModel>()

        coEvery { getCityUseCase() } returns flowOf(cityName)
        coEvery { getCurrentWeatherUseCase(WeatherRequest(cityName)) } returns fakeWeather

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            skipItems(3)
            val successState = awaitItem()
            assertEquals(fakeWeather, successState.weather)
            assertNull(successState.errorMessage)
            assertFalse(successState.isLoading)
        }
    }

    @Test
    fun `getCityName with multiple city emissions should fetch weather for each city`() = runTest {
        val cityFlow = flowOf("Paris", "Tokyo")
        val parisWeather = mockk<WeatherModel>()
        val tokyoWeather = mockk<WeatherModel>()

        coEvery { getCityUseCase() } returns cityFlow
        coEvery { getCurrentWeatherUseCase(WeatherRequest("Paris")) } returns parisWeather
        coEvery { getCurrentWeatherUseCase(WeatherRequest("Tokyo")) } returns tokyoWeather

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            awaitItem() // initial state
            awaitItem() // loading state
            awaitItem() // Paris city name
            val parisWeatherState = awaitItem()
            assertEquals("Paris", parisWeatherState.cityName)
            assertEquals(parisWeather, parisWeatherState.weather)

            awaitItem() // Tokyo city name
            val tokyoWeatherState = awaitItem()
            assertEquals("Tokyo", tokyoWeatherState.cityName)
            assertEquals(tokyoWeather, tokyoWeatherState.weather)
        }
    }

    @Test
    fun `initial state should have default values`() = runTest {
        coEvery { getCityUseCase() } returns flowOf("Amman")
        coEvery { getCurrentWeatherUseCase(any()) } returns mockk()

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            val initialState = awaitItem()
            assertNull(initialState.weather)
            assertNull(initialState.cityName)
            assertFalse(initialState.isLoading)
            assertNull(initialState.errorMessage)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `error during weather fetch should not update weather data`() = runTest {
        val cityName = "Berlin"
        val errorMessage = "API Error"

        coEvery { getCityUseCase() } returns flowOf(cityName)
        coEvery { getCurrentWeatherUseCase(WeatherRequest(cityName)) } throws Exception(errorMessage)

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            skipItems(3)
            val errorState = awaitItem()
            assertNull(errorState.weather)
            assertEquals(errorMessage, errorState.errorMessage)
            assertFalse(errorState.isLoading)
        }
    }

    @Test
    fun `cityName should be updated before weather is fetched`() = runTest {
        val cityName = "Madrid"
        val fakeWeather = mockk<WeatherModel>()

        coEvery { getCityUseCase() } returns flowOf(cityName)
        coEvery { getCurrentWeatherUseCase(WeatherRequest(cityName)) } returns fakeWeather

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            awaitItem() // initial
            awaitItem() // loading
            val cityState = awaitItem()
            assertEquals(cityName, cityState.cityName)
            assertNull(cityState.weather) // weather not yet fetched

            val weatherState = awaitItem()
            assertEquals(cityName, weatherState.cityName)
            assertEquals(fakeWeather, weatherState.weather)
        }
    }

    @Test
    fun `loading state should be false after error`() = runTest {
        val cityName = "Rome"

        coEvery { getCityUseCase() } returns flowOf(cityName)
        coEvery { getCurrentWeatherUseCase(any()) } throws Exception("Connection timeout")

        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getCityUseCase
        )

        viewModel.homeState.test {
            awaitItem() // initial
            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            skipItems(1)
            val errorState = awaitItem()
            assertFalse(errorState.isLoading)
        }
    }
}