package com.example.tradfriclient.data.repository

import com.example.tradfriclient.data.model.response.details.RoomDevicesResponse
import com.example.tradfriclient.data.model.response.rooms.RoomResponse
import com.example.tradfriclient.data.repository.datasource.rooms.RoomsRemoteDataSource
import com.example.tradfriclient.data.util.Resource
import com.example.tradfriclient.data.util.toResource
import com.example.tradfriclient.domain.repository.RoomsRepository

class RoomsRepositoryImpl(
    private val remoteDataSource: RoomsRemoteDataSource
) : RoomsRepository {
    override suspend fun getRooms(): Resource<RoomResponse> =
        remoteDataSource.getRooms().toResource()

    override suspend fun getRoomDevices(deviceId: Int): Resource<RoomDevicesResponse> =
        remoteDataSource.getRoomDevices(deviceId).toResource()
}