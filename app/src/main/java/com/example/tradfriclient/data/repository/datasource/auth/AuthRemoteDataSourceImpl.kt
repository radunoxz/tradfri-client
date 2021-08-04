package com.example.tradfriclient.data.repository.datasource.auth

import com.example.tradfriclient.data.api.TradfriService
import com.example.tradfriclient.data.model.request.LoginRequest
import com.example.tradfriclient.data.model.response.login.LoginResponse
import retrofit2.Response

class AuthRemoteDataSourceImpl(
    private val apiService: TradfriService
) : AuthRemoteDataSource {

    override suspend fun login(securityCode: String, hostIP: String): Response<LoginResponse> =
        apiService.login(LoginRequest(securityCode, hostIP))
}