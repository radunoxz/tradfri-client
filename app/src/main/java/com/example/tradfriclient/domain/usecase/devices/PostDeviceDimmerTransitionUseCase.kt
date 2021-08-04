package com.example.tradfriclient.domain.usecase.devices

import com.example.tradfriclient.domain.repository.DevicesRepository

class PostDeviceDimmerTransitionUseCase(
    private val repository: DevicesRepository
) {
    suspend fun execute(deviceId: Int, dimmerValue: Int, transitionTime: Int) =
        repository.postDeviceDimmerTransition(deviceId, dimmerValue, transitionTime)
}