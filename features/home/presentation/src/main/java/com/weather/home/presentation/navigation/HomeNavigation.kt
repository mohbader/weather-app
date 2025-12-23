package com.weather.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.weather.home.presentation.HomeScreen
import com.weather.navigation.MainNav
import com.weather.navigation.OnNavigateTo

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(route = MainNav.MainRout.Home, navOptions)

fun NavGraphBuilder.homeScreen() {
    composable<MainNav.MainRout.Home> {
        HomeScreen()
    }
}