package com.core.worker

import kotlin.random.Random

data class NotificationModel(
    var id: Int = Random.nextInt(),
    var title: String,
    var message: String,
    var type: String
)