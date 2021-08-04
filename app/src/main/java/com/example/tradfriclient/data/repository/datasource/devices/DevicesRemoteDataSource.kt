package com.example.tradfriclient.data.repository.datasource.devices

import retrofit2.Response
import retrofit2.http.Path

interface DevicesRemoteDataSource {
    suspend fun postDeviceState(deviceId: Int, deviceState: Int): Response<Unit>
    suspend fun postDeviceDimmerTransition(
        deviceId: Int,
        dimmerValue: Int,
        transitionTime: Int
    ): Response<Unit>
    suspend fun postDeviceColor(deviceId: Int, color: String): Response<Unit>
}