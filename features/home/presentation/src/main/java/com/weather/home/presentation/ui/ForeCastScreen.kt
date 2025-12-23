package com.weather.home.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weather.home.domain.model.WeatherConditionModel
import com.weather.home.presentation.util.ForecastItem
import com.weather.navigation.MainNav

@Composable
fun ForeCastScreen(list: List<WeatherConditionModel>?) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(
            list?.size ?: 0,
        ) { index ->
            val item = list?.get(index)
            if (item != null) {
                ForecastItem(item)
            }


        }
    }
}