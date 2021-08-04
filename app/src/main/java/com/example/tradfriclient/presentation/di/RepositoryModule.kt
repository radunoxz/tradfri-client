package com.example.tradfriclient.presentation.di

import com.example.tradfriclient.data.repository.AuthRepositoryImpl
import com.example.tradfriclient.data.repository.DevicesRepositoryImpl
import com.example.tradfriclient.data.repository.RoomsRepositoryImpl
import com.example.tradfriclient.data.repository.datasource.auth.AuthRemoteDataSource
import com.example.tradfriclient.data.repository.datasource.devices.DevicesRemoteDataSource
import com.example.tradfriclient.data.repository.datasource.rooms.RoomsRemoteDataSource
import com.example.tradfriclient.domain.repository.AuthRepository
import com.example.tradfriclient.domain.repository.DevicesRepository
import com.example.tradfriclient.domain.repository.RoomsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(authDataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepositoryImpl(authDataSource)

    @Provides
    @Singleton
    fun provideRoomsRepository(roomsRemoteDataSource: RoomsRemoteDataSource): RoomsRepository =
        RoomsRepositoryImpl(roomsRemoteDataSource)

    @Provides
    @Singleton
    fun provideDevicesRepository(devicesRemoteDataSource: DevicesRemoteDataSource): DevicesRepository =
        DevicesRepositoryImpl(devicesRemoteDataSource)
}