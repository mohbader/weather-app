package com.weather.home.data.remotesource

import com.weather.home.data.remotesource.model.CurrentWeatherRemoteResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HomeRemoteDataSource {
    @GET("weather.ashx")
    suspend fun getCurrentWeather(
        @QueryMap queryMap: Map<String, String>
    ): CurrentWeatherRemoteResponse
}