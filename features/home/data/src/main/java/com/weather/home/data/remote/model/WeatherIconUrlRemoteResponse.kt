package com.weather.home.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherIconUrlRemoteResponse(
    @Json(name = "value")
    val iconUrl: String? = null
)