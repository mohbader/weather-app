package com.weather.home.presentation.ui

import android.provider.CalendarContract
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.weather.home.domain.model.WeatherConditionModel
import com.weather.home.presentation.R
import com.weather.home.presentation.util.WeatherInfoCard

@Composable
fun CurrentConditionScreen(weather: WeatherConditionModel, cityName: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(vertical = 4.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            shape = RoundedCornerShape(3.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White, text = cityName
                )

                Text(
                    fontSize = 14.sp,
                    color = Color.LightGray, text = weather.time.orEmpty()
                )

                AsyncImage(
                    model = weather.weatherIcon,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White, text = weather.temperature.orEmpty()
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    text = stringResource(R.string.feels_like) + weather.feelsLikeC

                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxHeight(0.25f),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            WeatherInfoCard(
                title = R.string.wind_speed,
                value = weather.windSpeedKm.orEmpty(),
                Modifier.weight(1f)
            )
            WeatherInfoCard(
                title = R.string.humidity,
                value = weather.humidity.orEmpty(),
                Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxHeight(0.25f),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            WeatherInfoCard(
                title = R.string.visibility,
                value = weather.visibility.orEmpty(),
                Modifier.weight(1f)
            )
            WeatherInfoCard(title = R.string.uv_index,
                value = weather.uvIndex.orEmpty(),
                Modifier.weight(1f)
            )
        }
    }
}


@Preview
@Composable
fun CurrentConditionScreenPreview() {
    CurrentConditionScreen(weather = WeatherConditionModel(), "Amman")
}