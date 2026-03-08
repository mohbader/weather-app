package com.weather.home.presentation.state

import com.weather.home.domain.model.WeatherModel

data class HomeState(
    val weather: WeatherModel? = null,
    val cityName: String? = null,
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: String? = null
)