package com.example.tradfriclient.data.repository

import com.example.tradfriclient.data.repository.datasource.devices.DevicesRemoteDataSource
import com.example.tradfriclient.data.repository.datasource.rooms.RoomsRemoteDataSource
import com.example.tradfriclient.data.util.Resource
import com.example.tradfriclient.data.util.toResource
import com.example.tradfriclient.domain.repository.DevicesRepository

class DevicesRepositoryImpl(
    private val remoteDataSource: DevicesRemoteDataSource
) : DevicesRepository {
    override suspend fun postDeviceState(deviceId: Int, deviceState: Int): Resource<Unit> =
        remoteDataSource.postDeviceState(deviceId, deviceState).toResource()

    override suspend fun postDeviceDimmerTransition(
        deviceId: Int,
        dimmerValue: Int,
        transitionTime: Int
    ): Resource<Unit> =
        remoteDataSource.postDeviceDimmerTransition(
            deviceId,
            dimmerValue,
            transitionTime
        ).toResource()

    override suspend fun postDeviceColor(deviceId: Int, color: String): Resource<Unit> =
        remoteDataSource.postDeviceColor(deviceId, color).toResource()
}