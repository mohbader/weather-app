package com.weather.navigation

import kotlinx.serialization.Serializable


sealed class MainNav : Navigable {
    sealed class MainRout : MainNav() {
        @Serializable
        data object Home : MainRout()

        @Serializable
        data object Search : MainRout()
    }
}
