package com.weather.datastore.di

import com.weather.datastore.PreferencesRepository
import com.weather.datastore.PreferencesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface PreferenceFeatureModule {

    @Binds
    fun bindHomeRepository(impl: PreferencesRepositoryImpl): PreferencesRepository
}