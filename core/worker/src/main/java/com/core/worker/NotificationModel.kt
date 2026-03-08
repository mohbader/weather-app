package com.core.worker

data class NotificationModel(
    var id: Int = Math.random().toInt(),
    var title: String,
    var message: String,
    var type: String
)