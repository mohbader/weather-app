package com.core.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val CHANNEL_ID_UPDATES = "channel_updates"
        const val CHANNEL_ID_ALERTS = "channel_alerts"
    }

    fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val manager = context.getSystemService(NotificationManager::class.java)

        manager.createNotificationChannels(
            listOf(
                NotificationChannel(
                    CHANNEL_ID_UPDATES,
                    "Hourly Updates",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Background API update notifications"
                },
                NotificationChannel(
                    CHANNEL_ID_ALERTS,
                    "Important Alerts",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Critical alerts requiring attention"
                    enableVibration(true)
                }
            )
        )
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showUpdateNotification(response: NotificationModel) {

        val channelId = when (response.type) {
            "alert" -> CHANNEL_ID_ALERTS
            else -> CHANNEL_ID_UPDATES
        }


        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(response.title)
            .setContentText(response.message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(response.message)
            )
            .setPriority(
                if (response.type == "alert")
                    NotificationCompat.PRIORITY_HIGH
                else
                    NotificationCompat.PRIORITY_DEFAULT
            )
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context)
            .notify(response.id, notification)
    }
}