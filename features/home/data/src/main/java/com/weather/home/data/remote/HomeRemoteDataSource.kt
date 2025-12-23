package com.weather.home.data.remote

import com.weather.home.data.remote.model.WeatherDataRemoteSource
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HomeRemoteDataSource {
    @GET("weather.ashx")
    suspend fun getWeather(
        @QueryMap queryMap: Map<String, String>
    ): WeatherDataRemoteSource
}