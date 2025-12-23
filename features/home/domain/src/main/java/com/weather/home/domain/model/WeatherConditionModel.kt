package com.weather.home.domain.model

data class WeatherConditionModel(
    val temperature: String? = null,
    val weatherIcon: String? = null,
    val weatherDesc: String? = null,
    val feelsLikeC: String? = null,
    val humidity: String? = null,
    val uvIndex: String? = null,
    val windSpeedKm: String? = null,
    val visibility: String? = null,
    val time: String? = null
)