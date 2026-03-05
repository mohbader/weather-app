package com.weather.datastore.usecases

import com.weather.datastore.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityUseCase @Inject  constructor(
     val preferencesRepository: PreferencesRepository
) {

    suspend operator fun invoke(): Flow<String> {
        return preferencesRepository.getCityName()
    }
}