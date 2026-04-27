package com.weather.presentation.state

import com.weather.domain.model.CityModel

data class SearchState(
    var searchQuery: String = "",
    var cities: List<CityModel>? = emptyList(),
    val selectedCityName: String? = null,
    var isLoading: Boolean = false,
    var errorMessage: String? = null
)
