package com.weather.search.data.di

import com.weather.search.data.remote.SearchRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
internal object SearchRemoteModule {

    @Provides
    fun provideSearchRemoteDataSource(retrofit: Retrofit): SearchRemoteDataSource {
        return retrofit.create()
    }
}