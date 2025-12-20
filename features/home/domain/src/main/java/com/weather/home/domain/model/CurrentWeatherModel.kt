package com.weather.home.domain.model

data class CurrentWeatherModel(
    val cityName: String,
    val temperature: String,
    val weatherIcon: String,
    val weatherDesc: String,
)