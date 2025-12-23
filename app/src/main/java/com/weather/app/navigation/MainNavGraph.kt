package com.weather.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.weather.home.presentation.navigation.homeScreen
import com.weather.navigation.MainNav
import com.weather.navigation.Navigable
import com.weather.navigation.PreviousScreen
import com.weather.presentation.navigation.searchScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: MainNav.MainRout
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.systemBarsPadding(),
        ) {
            homeScreen()

            searchScreen() { destination, optionsBuilder ->
                navController.navigateTo(destination, navOptions(optionsBuilder))
            }
        }
    }
}

private fun NavHostController.navigateTo(
    destination: Navigable,
    navOptions: NavOptions?,
) {
    when (destination) {
        is PreviousScreen -> {
            val isBackStackEmpty = previousBackStackEntry == null
            if (!isBackStackEmpty) popBackStack()
        }

        else -> navigate(destination, navOptions)
    }
}