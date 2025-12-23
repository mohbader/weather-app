package com.weather.home.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDescRemoteDataSource(
    @Json(name = "value")
    val weatherDesc: String? = null
)