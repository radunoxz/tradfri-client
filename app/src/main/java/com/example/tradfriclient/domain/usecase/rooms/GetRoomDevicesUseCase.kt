package com.example.tradfriclient.domain.usecase.rooms

import com.example.tradfriclient.domain.repository.RoomsRepository

class GetRoomDevicesUseCase(
    private val repository: RoomsRepository
) {
    suspend fun execute(deviceId: Int) = repository.getRoomDevices(deviceId)
}