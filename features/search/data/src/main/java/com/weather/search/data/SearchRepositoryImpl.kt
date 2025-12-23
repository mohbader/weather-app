package com.weather.search.data

import com.weather.domain.SearchRepository
import com.weather.domain.model.SearchRequestModel
import com.weather.domain.model.CityModel
import com.weather.network.ApiResult
import com.weather.network.safeApiCall
import com.weather.search.data.mapper.toDomainModelList
import com.weather.search.data.remote.SearchRemoteDataSource
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource
) : SearchRepository {

    override suspend fun searchCity(searchRequestModel: SearchRequestModel): List<CityModel> {
        val mapRequest = mapOf("query" to searchRequestModel.cityName)
        return when (val result = safeApiCall { remoteDataSource.searchCity(mapRequest) }) {
            is ApiResult.Success -> result.data.searchApi?.toDomainModelList() ?: emptyList()
            is ApiResult.Error -> throw result.exception
        }
    }
}