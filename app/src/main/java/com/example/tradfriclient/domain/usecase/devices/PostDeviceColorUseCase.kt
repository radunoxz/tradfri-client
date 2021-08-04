package com.example.tradfriclient.domain.usecase.devices

import com.example.tradfriclient.domain.repository.DevicesRepository

class PostDeviceColorUseCase(
    private val repository: DevicesRepository
) {
    suspend fun execute(deviceId: Int, color: String) =
        repository.postDeviceColor(deviceId, color)
}