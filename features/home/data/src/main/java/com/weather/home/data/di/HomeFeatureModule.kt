package com.weather.home.data.di

import com.weather.home.data.HomeRepositoryImpl
import com.weather.home.domain.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface HomeFeatureModule {

    @Binds
    fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository
}