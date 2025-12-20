package com.weather.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class QueryParameterInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val url = originalRequest.url.newBuilder()
            .addQueryParameter("format", "json")
            .addQueryParameter("key", BuildConfig.WEATHER_API_KEY)
            .build()

        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}