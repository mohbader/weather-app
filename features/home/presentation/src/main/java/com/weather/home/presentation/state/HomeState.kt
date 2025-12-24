package com.weather.home.presentation.state

import com.weather.home.domain.model.WeatherModel

data class HomeState(
    val weather: WeatherModel? = null,
    val cityName: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)