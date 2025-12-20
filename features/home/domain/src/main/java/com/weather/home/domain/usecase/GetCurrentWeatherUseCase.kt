package com.weather.home.domain.usecase

import com.weather.home.domain.HomeRepository
import com.weather.home.domain.model.CurrentWeatherModel
import com.weather.home.domain.model.CurrentWeatherRequest


class GetCurrentWeatherUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(currentWeatherRequest: CurrentWeatherRequest): CurrentWeatherModel {
        return homeRepository.getCurrentWeather(currentWeatherRequest)

    }
}