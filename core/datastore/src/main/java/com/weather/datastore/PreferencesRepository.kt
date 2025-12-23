package com.weather.datastore

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setCityName(cityName: String)
     fun getCityName(): Flow<String?>
     fun isCitySaved(): Flow<Boolean>
}

