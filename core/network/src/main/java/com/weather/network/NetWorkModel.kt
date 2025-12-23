package com.weather.network

import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModel {

    @Provides
    @Singleton
    fun provideQueryInterceptor(): QueryParameterInterceptor {
        return QueryParameterInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        queryInterceptor: QueryParameterInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(queryInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(Wrapped.ADAPTER_FACTORY)
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient,moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }
}