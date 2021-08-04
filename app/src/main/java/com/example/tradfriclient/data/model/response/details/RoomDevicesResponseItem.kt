package com.example.tradfriclient.data.model.response.details


import com.google.gson.annotations.SerializedName

data class RoomDevicesResponseItem(
    @SerializedName("dimmer")
    val dimmer: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("info")
    val info: Info,
    @SerializedName("lastSeen")
    val lastSeen: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("reachable")
    val reachable: Boolean,
    @SerializedName("state")
    val state: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("typeId")
    val typeId: Int
)