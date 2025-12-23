package com.weather.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.datastore.usecases.GetCityUseCase
import com.weather.home.domain.model.WeatherRequest
import com.weather.home.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getCityUseCase: GetCityUseCase
) : ViewModel() {

    private val _cityState = MutableStateFlow<String?>(null)
    var cityState = _cityState.asStateFlow()

    init {
        getCityName()
    }

    private fun getCityName() {
        viewModelScope.launch {
            getCityUseCase().collect { cityName ->
                _cityState.value = cityName
                getWeather(cityName)
            }
        }
    }

    private fun getWeather(city: String?) {
        if (city?.isNotBlank() == true && city.isNotEmpty()) {
            viewModelScope.launch {
                getCurrentWeatherUseCase(WeatherRequest(city))

            }
        }
    }

}