package com.weather.home.domain.model

data class WeatherModel(
    val weatherConditionModel: WeatherConditionModel? = null,
    val forecast: List<WeatherConditionModel>?=null
)