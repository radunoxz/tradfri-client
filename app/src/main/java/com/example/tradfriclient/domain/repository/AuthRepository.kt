package com.example.tradfriclient.domain.repository

import com.example.tradfriclient.data.model.response.login.LoginResponse
import com.example.tradfriclient.data.util.Resource

interface AuthRepository {
    suspend fun login(securityCode: String, hostIP:String): Resource<LoginResponse>
}