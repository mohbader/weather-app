package com.weather.search.data.di

import com.weather.domain.SearchRepository
import com.weather.search.data.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface SearchFeatureModule {

    @Binds
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository
}