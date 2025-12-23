package com.weather.datastore.usecases

import com.weather.datastore.PreferencesRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(cityName: String) {
        return preferencesRepository.setCityName(cityName)
    }
}