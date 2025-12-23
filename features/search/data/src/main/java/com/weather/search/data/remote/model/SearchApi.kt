package com.weather.search.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchApi(
    @Json(name = "result")
    val result: List<Result?>?
)