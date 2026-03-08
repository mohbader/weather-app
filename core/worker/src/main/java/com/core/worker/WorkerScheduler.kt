package com.core.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerScheduler @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun scheduleHourlyWork(cityName: String) {

        val inputData = workDataOf(
            WeatherApiUpdate.CITY_NAME to cityName
        )
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequestBuilder<WeatherApiUpdate>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .addTag(WeatherApiUpdate.WORK_TAG)
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WeatherApiUpdate.WORK_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    fun cancelWork() {
        WorkManager.getInstance(context).cancelAllWorkByTag(WeatherApiUpdate.WORK_TAG)
    }
}