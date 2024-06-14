package com.example.indoquest.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.indoquest.api.ApiConfig
import com.example.indoquest.api.RegisterBody
import com.example.indoquest.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse : LiveData<RegisterResponse> = _registerResponse

    fun postRegister(authBody : RegisterBody) {
        ApiConfig.getApiService().postRegister(authBody).enqueue(object :
            Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if(response.isSuccessful){
                    _registerResponse.postValue(response.body())
                    Log.d("TAG", response.body().toString())
                }else{
                    _registerResponse.postValue(RegisterResponse(true, "null"))
                    Log.d("TAG", "Register Response Failed")
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _registerResponse.postValue(RegisterResponse(true, "null"))
                Log.d("TAG", t.message.toString())
            }
        })
    }
}