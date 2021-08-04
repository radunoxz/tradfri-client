package com.example.tradfriclient.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradfriclient.data.model.response.login.LoginResponse
import com.example.tradfriclient.data.util.Resource
import com.example.tradfriclient.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usecase: LoginUseCase
) : ViewModel() {
    val test: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    fun login(securityCode: String, hostIP: String) = viewModelScope.launch(Dispatchers.IO) {
        val apiResponse = usecase.execute(securityCode, hostIP)
        test.postValue(apiResponse)
    }
}