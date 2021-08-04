package com.example.tradfriclient.data.util

import retrofit2.Response

fun <T> Response<T>.toResource(): Resource<T> {
    if (this.isSuccessful) {
        this.body()?.let { result ->
            return Resource.Success(result)
        }
    }
    return Resource.Error(this.message())
}