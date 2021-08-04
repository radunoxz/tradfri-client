package com.example.tradfriclient.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context.applicationContext
    }
}
