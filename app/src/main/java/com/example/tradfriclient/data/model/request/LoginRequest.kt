package com.example.tradfriclient.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("code")
    val code: String,
    @SerializedName("host")
    val host: String
)