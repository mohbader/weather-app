package com.weather.home.presentation.util

import com.weather.home.presentation.R

enum class HomeTabs(val title: Int, val index: Int) {

    CURRENT_CONDITION(
        R.string.current,
        0
    ),

    FORECAST(
        R.string.seven_day_forecasting,
        1
    ),
}