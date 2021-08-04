package com.example.tradfriclient.presentation.di

import android.content.Context
import com.example.tradfriclient.BuildConfig
import com.example.tradfriclient.data.api.TradfriService
import com.example.tradfriclient.data.util.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(context))
            .baseUrl(BuildConfig.BASE_URL).build()

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): TradfriService =
        retrofit.create(TradfriService::class.java)
}