package com.example.tradfriclient.data.repository.datasource.auth

import com.example.tradfriclient.data.model.response.login.LoginResponse
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun login(securityCode: String, hostIP:String): Response<LoginResponse>
}