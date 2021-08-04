package com.example.tradfriclient.data.repository.datasource.devices

import com.example.tradfriclient.data.api.TradfriService
import retrofit2.Response

class DevicesRemoteDataSourceImpl(
    private val apiService: TradfriService
) : DevicesRemoteDataSource {
    override suspend fun postDeviceState(deviceId: Int, deviceState: Int) =
        apiService.postDeviceState(deviceId, deviceState)

    override suspend fun postDeviceDimmerTransition(
        deviceId: Int,
        dimmerValue: Int,
        transitionTime: Int
    ): Response<Unit> =
        apiService.postDeviceDimmerTransition(deviceId, dimmerValue, transitionTime)

    override suspend fun postDeviceColor(deviceId: Int, color: String): Response<Unit> =
        apiService.postDeviceColor(deviceId, color)
}