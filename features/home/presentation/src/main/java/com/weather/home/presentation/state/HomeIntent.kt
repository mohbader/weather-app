package com.weather.home.presentation.state

sealed class HomeIntent {
    object GetCity : HomeIntent()
    data class GetWeather(var cityName: String) : HomeIntent()
}