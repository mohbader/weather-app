package com.weather.home.domain.usecase

import com.weather.home.domain.HomeRepository
import com.weather.home.domain.model.WeatherModel
import com.weather.home.domain.model.WeatherRequest
import javax.inject.Inject


class GetCurrentWeatherUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(weatherRequest: WeatherRequest): WeatherModel {
        return homeRepository.getWeather(weatherRequest)

    }
}