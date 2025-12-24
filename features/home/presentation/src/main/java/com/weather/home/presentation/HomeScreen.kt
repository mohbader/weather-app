package com.weather.home.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.weather.home.domain.model.WeatherModel
import com.weather.home.presentation.ui.CurrentConditionScreen
import com.weather.home.presentation.ui.ForeCastScreen
import com.weather.home.presentation.util.HomeTabs


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val uiState = viewModel.homeState.collectAsStateWithLifecycle().value
    val cityName = uiState.cityName
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        uiState.weather?.let {
            HomeScreenTab(weatherModel = it, cityName)
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        uiState.errorMessage?.let {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }

    }

}

@Composable
fun HomeScreenTab(weatherModel: WeatherModel, city: String?) {
    var selectedTab by remember { mutableIntStateOf(HomeTabs.CURRENT_CONDITION.index) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTab,
        ) {
            HomeTabs.entries.forEach { tab ->
                Tab(
                    selected = selectedTab == tab.index,
                    onClick = { selectedTab = tab.index },
                    text = { Text(text = stringResource(tab.title)) }
                )
            }
        }
        when (selectedTab) {
            HomeTabs.CURRENT_CONDITION.index -> {
                weatherModel.weatherConditionModel?.let {
                    CurrentConditionScreen(
                        it,
                        city.orEmpty()
                    )
                }
            }

            HomeTabs.FORECAST.index -> {
                weatherModel.forecast?.let {
                    ForeCastScreen(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeTabPreview() {
    HomeScreenTab(WeatherModel(), "Amman")
}