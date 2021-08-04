package com.example.tradfriclient.data.repository

import com.example.tradfriclient.data.model.response.login.LoginResponse
import com.example.tradfriclient.data.repository.datasource.auth.AuthRemoteDataSource
import com.example.tradfriclient.data.util.Resource
import com.example.tradfriclient.data.util.toResource
import com.example.tradfriclient.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val dataSource: AuthRemoteDataSource
): AuthRepository {
    override suspend fun login(securityCode: String, hostIP: String): Resource<LoginResponse> =
        dataSource.login(securityCode, hostIP).toResource()
}