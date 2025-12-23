package com.weather.search.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchRemoteResponse(
    @Json(name = "search_api")
    val searchApi: SearchApi?
)