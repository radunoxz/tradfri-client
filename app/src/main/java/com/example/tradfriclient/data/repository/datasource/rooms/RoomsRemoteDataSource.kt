package com.example.tradfriclient.data.repository.datasource.rooms

import com.example.tradfriclient.data.model.response.details.RoomDevicesResponse
import com.example.tradfriclient.data.model.response.rooms.RoomResponse
import retrofit2.Response

interface RoomsRemoteDataSource {
    suspend fun getRooms(): Response<RoomResponse>
    suspend fun getRoomDevices(deviceId: Int): Response<RoomDevicesResponse>
}