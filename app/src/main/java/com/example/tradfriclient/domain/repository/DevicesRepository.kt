package com.example.tradfriclient.domain.repository

import com.example.tradfriclient.data.util.Resource
import retrofit2.Response

interface DevicesRepository {
    suspend fun postDeviceState(deviceId: Int, deviceState: Int): Resource<Unit>
    suspend fun postDeviceDimmerTransition(
        deviceId: Int,
        dimmerValue: Int,
        transitionTime: Int
    ): Resource<Unit>
    suspend fun postDeviceColor(deviceId: Int, color: String): Resource<Unit>
}