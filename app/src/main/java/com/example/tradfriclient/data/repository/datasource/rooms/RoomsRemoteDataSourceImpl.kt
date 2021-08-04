package com.example.tradfriclient.data.repository.datasource.rooms

import com.example.tradfriclient.data.api.TradfriService
import com.example.tradfriclient.data.model.response.rooms.RoomResponse
import retrofit2.Response

class RoomsRemoteDataSourceImpl(
    private val apiService: TradfriService
) : RoomsRemoteDataSource {
    override suspend fun getRooms(): Response<RoomResponse> = apiService.getGroups()

    override suspend fun getRoomDevices(deviceId: Int) = apiService.getGroupDevices(deviceId)

}