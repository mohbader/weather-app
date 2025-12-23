package com.weather.home.data.mapper

import com.weather.home.data.remote.model.ConditionRemoteResponse
import com.weather.home.data.remote.model.WeatherRemoteResponse
import com.weather.home.domain.model.WeatherConditionModel
import com.weather.home.domain.model.WeatherModel

fun WeatherRemoteResponse.toDomainModel(): WeatherModel {
    return WeatherModel(
        weatherConditionModel = this.currentWeather?.firstOrNull()?.toWeatherConditionModel(),
        forecast = this.forecast?.flatMap { it.hourly.orEmpty() }
            ?.map { it.toWeatherConditionModel() } ?: emptyList()
    )
}

private fun ConditionRemoteResponse.toWeatherConditionModel(): WeatherConditionModel {
    return WeatherConditionModel(
        temperature = this.temp.orEmpty(),
        weatherIcon = this.weatherIcon?.firstOrNull()?.iconUrl.orEmpty(),
        weatherDesc = this.weatherDesc?.firstOrNull()?.weatherDesc.orEmpty(),
        feelsLikeC = this.feelsLikeC.orEmpty(),
        humidity = this.humidity.orEmpty(),
        uvIndex = this.uvIndex.orEmpty(),
        visibility = this.visibility.orEmpty(),
        windSpeedKm = this.windSpeedKm.orEmpty(),
        time = (this.observationTime ?: this.time).orEmpty()
    )
}
