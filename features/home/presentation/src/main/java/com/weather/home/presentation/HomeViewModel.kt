package com.weather.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.datastore.usecases.GetCityUseCase
import com.weather.home.domain.model.WeatherRequest
import com.weather.home.domain.usecase.GetCurrentWeatherUseCase
import com.weather.home.presentation.state.HomeIntent
import com.weather.home.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getCityUseCase: GetCityUseCase,
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    var homeState = _homeState
        .onStart {
            getCityName()
        }.stateIn(
            viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            _homeState.value
        )

    private fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.GetCity -> getCityName()
            is HomeIntent.GetWeather -> getWeather(intent.cityName)
        }
    }

    private fun getCityName() {
        viewModelScope.launch {
            getCityUseCase()
                .catch { exception ->
                    _homeState.update {
                        it.copy(
                            errorMessage = exception.message,
                            isLoading = false
                        )
                    }
                }
                .collect { cityName ->
                    _homeState.update { it.copy(cityName = cityName) }
                    getWeather(cityName)
                }
        }
    }

    private fun getWeather(city: String?) {
        if (city?.isNotBlank() == true && city.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val weatherResult = getCurrentWeatherUseCase(WeatherRequest(city))
                    _homeState.update {
                        it.copy(
                            weather = weatherResult,
                            isLoading = false,
                        )
                    }
                } catch (e: Exception) {
                    _homeState.update { it.copy(errorMessage = e.message, isLoading = false) }
                }
            }
        }
    }

}