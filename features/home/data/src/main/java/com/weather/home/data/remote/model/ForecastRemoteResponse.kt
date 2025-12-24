package com.weather.home.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastRemoteResponse(
    @Json(name = "date")
    val date: String? = null,

    @Json(name = "hourly")
    val hourly: List<ConditionRemoteResponse>? = null
)

