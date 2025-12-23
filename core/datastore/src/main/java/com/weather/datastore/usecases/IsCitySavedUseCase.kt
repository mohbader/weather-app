package com.weather.datastore.usecases

import com.weather.datastore.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsCitySavedUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return preferencesRepository.isCitySaved()
    }
}