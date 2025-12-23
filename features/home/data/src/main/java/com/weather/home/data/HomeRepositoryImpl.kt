package com.weather.home.data

import com.weather.home.data.mapper.toDomainModel
import com.weather.home.data.remote.HomeRemoteDataSource
import com.weather.home.domain.HomeRepository
import com.weather.home.domain.model.WeatherModel
import com.weather.home.domain.model.WeatherRequest
import com.weather.network.ApiResult
import com.weather.network.safeApiCall
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
) : HomeRepository {
    override suspend fun getWeather(weatherRequest: WeatherRequest): WeatherModel {
        val mapRequest = mapOf("query" to weatherRequest.cityName, "num_of_days" to "7")
        return when (val result = safeApiCall { remoteDataSource.getWeather(mapRequest) }) {
            is ApiResult.Success -> result.data.weatherRemoteResponse?.toDomainModel()
                ?: throw Exception("No data")

            is ApiResult.Error -> throw result.exception
        }
    }
}