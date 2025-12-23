package com.weather.home.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class WeatherRemoteResponse(
    @Json(name = "current_condition")
    val currentWeather: List<ConditionRemoteResponse>? = null,

    @Json(name = "weather")
    val forecast: List<ForecastRemoteResponse>? = null
)

