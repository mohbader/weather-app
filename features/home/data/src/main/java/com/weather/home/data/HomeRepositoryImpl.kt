package com.weather.home.data

import com.weather.home.data.mapper.toDomainModel
import com.weather.home.data.remotesource.HomeRemoteDataSource
import com.weather.home.domain.HomeRepository
import com.weather.home.domain.model.CurrentWeatherModel
import com.weather.home.domain.model.CurrentWeatherRequest
import com.weather.network.ApiResult
import com.weather.network.safeApiCall
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
) : HomeRepository {
    override suspend fun getCurrentWeather(currentWeatherRequest: CurrentWeatherRequest): CurrentWeatherModel {
        val mapRequest = mapOf("q" to currentWeatherRequest.cityName)
        return when (val result = safeApiCall { remoteDataSource.getCurrentWeather(mapRequest) }) {
            is ApiResult.Success -> result.data.toDomainModel()
            is ApiResult.Error -> throw result.exception
        }
    }
}