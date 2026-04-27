package com.weather.home.presentation.state

sealed interface HomeAction {
    object GetCity : HomeAction
    data class GetWeather(var cityName: String) : HomeAction
    data class OnTabSelected(val index: Int) : HomeAction

}