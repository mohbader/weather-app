package com.weather.search.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AreaName(
    @Json(name = "value")
    val value: String?
)