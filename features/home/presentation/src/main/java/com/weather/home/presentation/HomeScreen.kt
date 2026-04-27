package com.weather.home.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.weather.home.presentation.state.HomeAction
import com.weather.home.presentation.state.HomeState
import com.weather.home.presentation.ui.CurrentConditionScreen
import com.weather.home.presentation.ui.ForeCastScreen
import com.weather.home.presentation.util.HomeTabs


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val uiState = viewModel.homeState.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        uiState.weather?.let {
            HomeScreenTab(state = uiState, viewModel::onAction)
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        uiState.errorMessage?.let {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }

    }

}

@Composable
fun HomeScreenTab(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = state.selectedTabIndex,
        ) {
            HomeTabs.entries.forEach { tab ->
                Tab(
                    selected = state.selectedTabIndex == tab.index,
                    onClick = { onAction(HomeAction.OnTabSelected(tab.index)) },
                    text = { Text(text = stringResource(tab.title)) }
                )
            }
        }
        when (state.selectedTabIndex) {
            HomeTabs.CURRENT_CONDITION.index -> {
                state.weather?.weatherConditionModel?.let {
                    CurrentConditionScreen(
                        it,
                        state.cityName.orEmpty()
                    )
                }
            }

            HomeTabs.FORECAST.index -> {
                state.weather?.forecast?.let {
                    ForeCastScreen(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeTabPreview() {
    HomeScreenTab(HomeState(), {})
}