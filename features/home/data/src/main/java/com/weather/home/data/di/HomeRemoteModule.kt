package com.weather.home.data.di

import com.weather.home.data.remote.HomeRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
internal object HomeRemoteModule {

    @Provides
    fun provideHomeRemoteDataSource(retrofit: Retrofit): HomeRemoteDataSource {
        return retrofit.create()
    }
}