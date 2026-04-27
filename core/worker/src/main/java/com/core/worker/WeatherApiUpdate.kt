package com.core.worker

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.weather.home.domain.HomeRepository
import com.weather.home.domain.model.WeatherRequest
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class WeatherApiUpdate @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val homeRepository: HomeRepository,
    private val notificationHelper: NotificationHelper
) : CoroutineWorker(context, workerParams) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {
        return try {
            val cityName = inputData.getString(CITY_NAME) ?: return Result.failure()
            val data = homeRepository.getWeather(WeatherRequest(cityName))
            notificationHelper.showUpdateNotification(
                NotificationModel(
                    title = "Weather Update",
                    message = "The Temperature in $cityName is ${data.weatherConditionModel?.temperature} ",
                    type = "update"
                )
            )
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_TAG = "hourly_api_fetch"
        const val CITY_NAME = "city_name"
    }
}