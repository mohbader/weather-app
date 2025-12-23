package com.weather.home.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConditionRemoteResponse(
    @Json(name = "temp_C")
    val tempC: String? = null,

    @Json(name = "tempC")
    val tempCHourly: String? = null,

    @Json(name = "weatherIconUrl")
    val weatherIcon: List<WeatherIconUrlRemoteResponse>? = null,

    @Json(name = "weatherDesc")
    val weatherDesc: List<WeatherDescRemoteDataSource>? = null,

    @Json(name = "FeelsLikeC")
    val feelsLikeC: String? = null,

    @Json(name = "humidity")
    val humidity: String? = null,

    @Json(name = "uvIndex")
    val uvIndex: String? = null,

    @Json(name = "windspeedKmph")
    val windSpeedKm: String? = null,

    @Json(name = "visibility")
    val visibility: String? = null,

    @Json(name = "observation_time")
    val observationTime: String? = null,

    @Json(name = "time")
    val time: String? = null
) {
    val temp: String?
        get() = tempC ?: tempCHourly
}