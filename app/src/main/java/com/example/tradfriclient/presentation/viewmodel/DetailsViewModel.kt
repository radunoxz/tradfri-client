package com.example.tradfriclient.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradfriclient.data.model.response.details.RoomDevicesResponse
import com.example.tradfriclient.data.util.Resource
import com.example.tradfriclient.domain.usecase.devices.PostDeviceColorUseCase
import com.example.tradfriclient.domain.usecase.devices.PostDeviceDimmerTransitionUseCase
import com.example.tradfriclient.domain.usecase.devices.PostDeviceStateUseCase
import com.example.tradfriclient.domain.usecase.rooms.GetRoomDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getRoomDevicesUseCase: GetRoomDevicesUseCase,
    private val postDeviceStateUseCase: PostDeviceStateUseCase,
    private val postDeviceDimmerTransitionUseCase: PostDeviceDimmerTransitionUseCase,
    private val postDeviceColorUseCase: PostDeviceColorUseCase
) : ViewModel() {
    val devicesList: MutableLiveData<Resource<RoomDevicesResponse>> = MutableLiveData()
    val stateList: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val lightDimmer: MutableLiveData<List<Int>> = MutableLiveData()

    fun getRoomDevices(roomId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val apiResponse = getRoomDevicesUseCase.execute(roomId)
        devicesList.postValue(apiResponse)
        lightDimmer.postValue(apiResponse.data?.map { it.dimmer })
    }

    fun setDeviceState(deviceId: Int, deviceState: Int, roomId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            val apiResponse = postDeviceStateUseCase.execute(deviceId, deviceState)
            stateList.postValue(apiResponse)
            val apiResponse2 = getRoomDevicesUseCase.execute(roomId)
            devicesList.postValue(apiResponse2)
        }

    fun setDeviceDimmerAndTransition(
        deviceId: Int,
        dimmerValue: Int,
        transitionTime: Int,
        roomId: Int
    ) =
        viewModelScope.launch(Dispatchers.IO) {
            val apiResponse =
                postDeviceDimmerTransitionUseCase.execute(deviceId, dimmerValue, transitionTime)
            stateList.postValue(apiResponse)
        }

    fun setDeviceColor(deviceId: Int, color: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val apiResponse =
                postDeviceColorUseCase.execute(deviceId, color)
            stateList.postValue(apiResponse)
        }
}