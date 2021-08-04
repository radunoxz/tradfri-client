package com.example.tradfriclient.domain.repository

import com.example.tradfriclient.data.model.response.details.RoomDevicesResponse
import com.example.tradfriclient.data.model.response.rooms.RoomResponse
import com.example.tradfriclient.data.util.Resource

interface RoomsRepository {
    suspend fun getRooms(): Resource<RoomResponse>
    suspend fun getRoomDevices(deviceId: Int): Resource<RoomDevicesResponse>
}