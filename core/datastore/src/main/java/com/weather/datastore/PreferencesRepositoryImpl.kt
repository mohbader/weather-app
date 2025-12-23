package com.weather.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferencesRepository {
    private object PreferencesKeys {
        val SELECTED_CITY = stringPreferencesKey("selected_city")
        val IS_CITY_SAVED = booleanPreferencesKey("is_city_saved")
    }

//    val selectedCity: Flow<String?> = dataStore.data
//        .map { preferences ->
//            preferences[PreferencesKeys.SELECTED_CITY]
//        }
//
//    val temperatureUnit: Flow<Boolean> = dataStore.data
//        .map { preferences ->
//            preferences[PreferencesKeys.IS_CITY_SAVED] ?: false
//        }
//
//    suspend fun saveSelectedCity(city: String) {
//        dataStore.edit { preferences ->
//            preferences[PreferencesKeys.SELECTED_CITY] = city
//        }
//    }

    suspend fun isCitySaved(isCitySaved: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_CITY_SAVED] = isCitySaved
        }
    }

    suspend fun clearAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override suspend fun setCityName(cityName: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SELECTED_CITY] = cityName
        }
        isCitySaved(true)
    }

    override  fun getCityName(): Flow<String?> =
        dataStore.data
            .map { preferences ->
                preferences[PreferencesKeys.SELECTED_CITY]
            }

    override  fun isCitySaved(): Flow<Boolean> =
        dataStore.data
            .map { preferences ->
                preferences[PreferencesKeys.IS_CITY_SAVED] ?: false
            }
}
