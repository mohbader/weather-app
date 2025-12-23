package com.weather.navigation

import androidx.navigation.NavOptionsBuilder

typealias OnNavigateTo = (Navigable, NavOptionsBuilder.() -> Unit) -> Unit
