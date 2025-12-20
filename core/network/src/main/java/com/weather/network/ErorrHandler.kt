package com.weather.network

import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> T): ApiResult<T> {
    return try {
        val response = call.invoke()
        ApiResult.Success(response)
    } catch (e: IOException) {
        ApiResult.Error(Exception("No internet connection", e))
    } catch (e: Exception) {
        ApiResult.Error(e)
    }
}
