package com.weather.home.data.remotesource.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastRemoteResponse(
    @field:Json(name = "hourly")
    val hourly: List<ConditionRemoteResponse>? = null
)

