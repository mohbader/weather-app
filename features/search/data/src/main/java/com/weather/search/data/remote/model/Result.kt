package com.weather.search.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "areaName")
    val areaName: List<AreaName?>?,

    @Json(name = "country")
    val country: List<Country?>?,
)