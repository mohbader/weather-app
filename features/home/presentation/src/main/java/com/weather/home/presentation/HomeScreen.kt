package com.weather.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.weather.home.domain.model.WeatherConditionModel
import com.weather.home.presentation.ui.CurrentConditionScreen
import com.weather.home.presentation.ui.ForeCastScreen
import com.weather.home.presentation.util.HomeTabs


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val cityName = viewModel.cityState.collectAsStateWithLifecycle().value.toString()

    HomeScreenTab()
}

@Composable
fun HomeScreenTab() {
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
            HomeTabs.CURRENT_CONDITION.index -> CurrentConditionScreen(
                WeatherConditionModel(),
                "Amman"
            )

            HomeTabs.FORECAST.index -> ForeCastScreen(listOf())
        }
    }
}

@Preview
@Composable
fun HomeTabPreview() {
    HomeScreenTab()
}