package com.example.tradfriclient.presentation.di

import com.example.tradfriclient.data.api.TradfriService
import com.example.tradfriclient.data.repository.DevicesRepositoryImpl
import com.example.tradfriclient.data.repository.datasource.auth.AuthRemoteDataSource
import com.example.tradfriclient.data.repository.datasource.auth.AuthRemoteDataSourceImpl
import com.example.tradfriclient.data.repository.datasource.devices.DevicesRemoteDataSource
import com.example.tradfriclient.data.repository.datasource.devices.DevicesRemoteDataSourceImpl
import com.example.tradfriclient.data.repository.datasource.rooms.RoomsRemoteDataSource
import com.example.tradfriclient.data.repository.datasource.rooms.RoomsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(apiService: TradfriService): AuthRemoteDataSource =
        AuthRemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideRoomsRemoteDataSource(apiService: TradfriService): RoomsRemoteDataSource =
        RoomsRemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideDevicesRemoteDataSource(apiService: TradfriService): DevicesRemoteDataSource =
        DevicesRemoteDataSourceImpl(apiService)
}