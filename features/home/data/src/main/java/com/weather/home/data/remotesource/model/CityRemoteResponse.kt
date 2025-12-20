package com.weather.home.data.remotesource.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityRemoteResponse(
    @field:Json(name = "query")
    val cityName: String
)