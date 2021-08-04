package com.example.tradfriclient.data.api

import com.example.tradfriclient.data.model.request.LoginRequest
import com.example.tradfriclient.data.model.response.details.RoomDevicesResponse
import com.example.tradfriclient.data.model.response.login.LoginResponse
import com.example.tradfriclient.data.model.response.rooms.RoomResponse
import retrofit2.Response
import retrofit2.http.*

interface TradfriService {
    @POST("gateway/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("groups")
    suspend fun getGroups(): Response<RoomResponse>

    @GET("groups/{id}/devices")
    suspend fun getGroupDevices(@Path("id") deviceId: Int): Response<RoomDevicesResponse>

    @PUT("devices/{id}/state/{state}")
    suspend fun postDeviceState(
        @Path("id") deviceId: Int,
        @Path("state") deviceState: Int
    ): Response<Unit>

    @PUT("devices/{id}/dimmer/{dimmer}/transition/{transition}")
    suspend fun postDeviceDimmerTransition(
        @Path("id") deviceId: Int,
        @Path("dimmer") dimmer: Int,
        @Path("transition") transition: Int
    ): Response<Unit>

    @PUT("devices/{id}/color/{color}")
    suspend fun postDeviceColor(
        @Path("id") deviceId: Int,
        @Path("color") color: String,
    ): Response<Unit>
}