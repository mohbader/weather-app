package com.weather.home.data.remotesource.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CurrentWeatherRemoteResponse(
    @field:Json(name = "request")
    val cityName: CityRemoteResponse? = null,

    @field:Json(name = "current_condition")
    val temperature: ConditionRemoteResponse?=null,
)

