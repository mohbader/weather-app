package com.weather.home.data

import com.weather.home.data.remotesource.HomeRemoteDataSource
import com.weather.home.domain.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
) : HomeRepository {
}