//package com.weather.presentation
//
//import com.weather.datastore.usecases.SaveCityUseCase
//import com.weather.domain.model.CityModel
//import com.weather.domain.usecase.SearchCityUseCase
//import io.mockk.coEvery
//import io.mockk.coVerify
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.setMain
//import org.junit.Assert.*
//import org.junit.Before
//import io.mockk.mockk
//import kotlinx.coroutines.test.advanceUntilIdle
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import org.junit.After
//import org.junit.Test
//import app.cash.turbine.test
//
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class SearchViewModelTest {
//
//    private val saveCityUseCase: SaveCityUseCase = mockk()
//    private val searchCityUseCase: SearchCityUseCase = mockk()
//
//    private lateinit var viewModel: SearchViewModel
//    private val testDispatcher = StandardTestDispatcher()
//
//    @Before
//    fun setup() {
//        // Set the Main dispatcher to our test dispatcher
//        Dispatchers.setMain(testDispatcher)
//        viewModel = SearchViewModel(saveCityUseCase, searchCityUseCase)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun `searchCity should update searchState with results from use case`() = runTest {
//        // Arrange
//        val cityName = "London"
//        val mockResults = listOf(CityModel(areaName = "London", country = "UK"))
//
//        // Use coEvery for suspend functions
//        coEvery { searchCityUseCase(any()) } returns mockResults
//
//        // Act
//        viewModel.searchCity(cityName)
//        advanceUntilIdle() // Wait for Coroutine to finish
//
//        // Assert
//        viewModel.searchState.test {
//            val result = awaitItem()
//            assertEquals(mockResults, result)
//        }
//    }
//
//    @Test
//    fun `saveCity should call saveCityUseCase once`() = runTest {
//        // Arrange
//        val cityName = "New York"
//        coEvery { saveCityUseCase(cityName) } returns Unit
//
//        // Act
//        viewModel.saveCity(cityName)
//        advanceUntilIdle()
//
//        // Assert
//        coVerify(exactly = 1) { saveCityUseCase(cityName) }
//    }
//}
//
//}