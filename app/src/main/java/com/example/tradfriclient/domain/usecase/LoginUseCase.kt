package com.example.tradfriclient.domain.usecase

import com.example.tradfriclient.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend fun execute(securityCode: String, hostIP: String) =
        repository.login(securityCode, hostIP)
}