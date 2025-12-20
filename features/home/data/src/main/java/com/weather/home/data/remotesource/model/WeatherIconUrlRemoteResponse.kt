package com.weather.home.data.remotesource.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherIconUrlRemoteResponse(
    @field:Json(name = "value")
    val iconUrl: String? = null
)