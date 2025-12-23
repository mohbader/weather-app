package com.weather.search.data.remote

import com.weather.search.data.remote.model.SearchRemoteResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchRemoteDataSource {
    @GET("search.ashx")
    suspend fun searchCity(
        @QueryMap queryMap: Map<String, String>
    ): SearchRemoteResponse
}
