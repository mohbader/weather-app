package com.weather.home.presentation.util

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weather.home.domain.model.WeatherConditionModel

@Composable
fun WeatherInfoCard(@StringRes title: Int, value: String,    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxHeight()
            .padding(vertical = 4.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(3.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.LightGray, text = stringResource(title)
            )

            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White, text = value
            )
        }
    }
}