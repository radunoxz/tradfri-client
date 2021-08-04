package com.example.tradfriclient.presentation.di

import com.example.tradfriclient.domain.repository.AuthRepository
import com.example.tradfriclient.domain.repository.DevicesRepository
import com.example.tradfriclient.domain.repository.RoomsRepository
import com.example.tradfriclient.domain.usecase.rooms.GetRoomsUseCase
import com.example.tradfriclient.domain.usecase.LoginUseCase
import com.example.tradfriclient.domain.usecase.devices.PostDeviceColorUseCase
import com.example.tradfriclient.domain.usecase.devices.PostDeviceDimmerTransitionUseCase
import com.example.tradfriclient.domain.usecase.devices.PostDeviceStateUseCase
import com.example.tradfriclient.domain.usecase.rooms.GetRoomDevicesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGetRoomsUseCase(roomsRepository: RoomsRepository): GetRoomsUseCase =
        GetRoomsUseCase(roomsRepository)

    @Provides
    @Singleton
    fun provideGetRoomDevicesUseCase(roomsRepository: RoomsRepository): GetRoomDevicesUseCase =
        GetRoomDevicesUseCase(roomsRepository)

    @Provides
    @Singleton
    fun providePostDeviceStateUseCase(devicesRepository: DevicesRepository): PostDeviceStateUseCase =
        PostDeviceStateUseCase(devicesRepository)

    @Provides
    @Singleton
    fun providePostDeviceDimmerTransitionUseCase(devicesRepository: DevicesRepository): PostDeviceDimmerTransitionUseCase =
        PostDeviceDimmerTransitionUseCase(devicesRepository)

    @Provides
    @Singleton
    fun providePostDeviceColorUseCase(devicesRepository: DevicesRepository): PostDeviceColorUseCase =
        PostDeviceColorUseCase(devicesRepository)
}