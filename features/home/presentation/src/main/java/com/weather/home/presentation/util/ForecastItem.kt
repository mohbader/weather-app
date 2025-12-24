package com.weather.home.presentation.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.weather.home.domain.model.WeatherConditionModel

@Composable
fun ForecastItem(weather: WeatherConditionModel) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(3.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White, text = weather.weatherDesc.orEmpty()
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.LightGray, text = weather.time.orEmpty()
                )


            }

            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                AsyncImage(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    model = weather.weatherIcon,
                    contentDescription = null,
                )

                Text(
                    fontSize = 12.sp,
                    color = Color.LightGray, text = weather.temperature.orEmpty()
                )
            }
        }
    }
}