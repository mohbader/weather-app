package com.weather.domain

import com.weather.domain.model.SearchRequestModel
import com.weather.domain.model.CityModel

interface SearchRepository {
    suspend fun searchCity(searchRequestModel: SearchRequestModel): List<CityModel>
}