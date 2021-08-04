package com.example.tradfriclient.domain.usecase.rooms

import com.example.tradfriclient.domain.repository.RoomsRepository

class GetRoomsUseCase(
    private val repository: RoomsRepository
) {
    suspend fun execute() = repository.getRooms()
}