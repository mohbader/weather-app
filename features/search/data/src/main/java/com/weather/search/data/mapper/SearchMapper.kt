package com.weather.search.data.mapper

import com.weather.domain.model.CityModel
import com.weather.search.data.remote.model.Result
import com.weather.search.data.remote.model.SearchApi

private fun Result.toDomainModel(): CityModel {
    return CityModel(
        areaName = this.areaName?.firstOrNull()?.value.orEmpty(),
        country = this.country?.firstOrNull()?.value.orEmpty()
    )
}

fun SearchApi.toDomainModelList(): List<CityModel> {
    return this.result?.mapNotNull { it?.toDomainModel() } ?: emptyList()
}
