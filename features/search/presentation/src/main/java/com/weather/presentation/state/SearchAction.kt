package com.weather.presentation.state

sealed interface SearchAction {
    data class OnSearchQueryChange(val query: String) : SearchAction

    data class OnCitySelected(val cityName: String) : SearchAction
}