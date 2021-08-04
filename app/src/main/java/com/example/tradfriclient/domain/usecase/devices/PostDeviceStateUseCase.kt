package com.example.tradfriclient.domain.usecase.devices

import com.example.tradfriclient.domain.repository.DevicesRepository

class PostDeviceStateUseCase(
    private val repository: DevicesRepository
) {
    suspend fun execute(deviceId: Int, deviceStatus: Int) =
        repository.postDeviceState(deviceId, deviceStatus)
}