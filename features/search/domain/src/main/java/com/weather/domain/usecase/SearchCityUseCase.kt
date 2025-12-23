package com.weather.domain.usecase

import com.weather.domain.SearchRepository
import com.weather.domain.model.SearchRequestModel
import com.weather.domain.model.CityModel
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(searchRequestModel: SearchRequestModel): List<CityModel> {
        return searchRepository.searchCity(searchRequestModel)
    }
}