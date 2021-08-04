package com.example.tradfriclient.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradfriclient.data.model.response.details.RoomDevicesResponse
import com.example.tradfriclient.data.model.response.rooms.RoomResponse
import com.example.tradfriclient.data.util.Resource
import com.example.tradfriclient.domain.usecase.rooms.GetRoomDevicesUseCase
import com.example.tradfriclient.domain.usecase.rooms.GetRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase
) : ViewModel() {
    val roomsList: MutableLiveData<Resource<RoomResponse>> = MutableLiveData()

    fun getRooms() = viewModelScope.launch(Dispatchers.IO) {
        val apiResponse = getRoomsUseCase.execute()
        roomsList.postValue(apiResponse)
    }
}