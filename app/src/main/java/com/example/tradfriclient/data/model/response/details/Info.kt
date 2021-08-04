package com.example.tradfriclient.data.model.response.details


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("batteryLevel")
    val batteryLevel: Any,
    @SerializedName("firmwareVersion")
    val firmwareVersion: String,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("modelNumber")
    val modelNumber: String,
    @SerializedName("powerSource")
    val powerSource: String,
    @SerializedName("powerSourceId")
    val powerSourceId: Int,
    @SerializedName("serial")
    val serial: String
)