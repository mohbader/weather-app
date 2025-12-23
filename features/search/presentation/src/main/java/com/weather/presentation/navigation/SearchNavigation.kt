package com.weather.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.weather.navigation.MainNav
import com.weather.navigation.OnNavigateTo
import com.weather.presentation.SearchScreen

fun NavController.navigateToSearch(navOptions: NavOptions) =
    navigate(route = MainNav.MainRout.Search, navOptions)

fun NavGraphBuilder.searchScreen(onNavigateTo: OnNavigateTo) {
    composable<MainNav.MainRout.Search> {
        SearchScreen(onNavigateTo = onNavigateTo)
    }
}