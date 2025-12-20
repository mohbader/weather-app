package com.weather.home.data.remotesource.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConditionRemoteResponse(
    @field:Json(name = "tempC")
    val temp: String? = null,

    @field:Json(name = "weatherIconUrl")
    val weatherIcon: List<WeatherIconUrlRemoteResponse>? = null,

    @field:Json(name = "weatherDesc")
    val weatherDesc: List<WeatherDescRemoteDataSource>? = null,

    @field:Json(name = "FeelsLikeC")
    val feelsLikeC: String? = null
)