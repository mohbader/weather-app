package com.weather.network

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import java.io.IOException

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
suspend fun <T : Any> safeApiCall(call: suspend () -> T): ApiResult<T> {
    return try {
        val response = call.invoke()
        ApiResult.Success(response)
    } catch (e: HttpException) {
        ApiResult.Error(e)
    } catch (e: IOException) {
        ApiResult.Error(Exception("No internet connection", e))
    } catch (e: Exception) {
        ApiResult.Error(e)
    }
}
