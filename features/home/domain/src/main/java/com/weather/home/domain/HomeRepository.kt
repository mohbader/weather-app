package com.weather.home.domain

import com.weather.home.domain.model.WeatherModel
import com.weather.home.domain.model.WeatherRequest

interface HomeRepository {

    suspend fun getWeather(weatherRequest: WeatherRequest): WeatherModel
}