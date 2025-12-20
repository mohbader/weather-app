package com.weather.home.domain

import com.weather.home.domain.model.CurrentWeatherModel
import com.weather.home.domain.model.CurrentWeatherRequest

interface HomeRepository {

    suspend fun getCurrentWeather(currentWeatherRequest: CurrentWeatherRequest): CurrentWeatherModel
}