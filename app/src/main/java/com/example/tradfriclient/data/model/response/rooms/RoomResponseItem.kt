package com.example.tradfriclient.data.model.response.rooms


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RoomResponseItem(
    @SerializedName("deviceIds")
    val deviceIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Serializable