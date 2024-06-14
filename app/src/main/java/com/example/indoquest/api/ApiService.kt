package com.example.indoquest.api

import com.example.indoquest.model.LoginResponse
import com.example.indoquest.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

const val UNEXPECTED_ERROR = "Unexpected Error"
const val UNEXPECTED_DATA_ERROR = "Unexpected Data Error"
const val CLIENT_ERROR = "Client Error"
const val SERVER_ERROR = "Server Error"

interface ApiService {
    @POST("register")
    fun postRegister(
        @Body authBody : RegisterBody
    ) : retrofit2.Call<RegisterResponse>

    @POST("login")
    fun postLogin(
        @Body authLoginBody: LoginBody
    ) : retrofit2.Call<LoginResponse>
}

fun formatResponseCode(code: Int): String {
    return if (code >= 500) {
        SERVER_ERROR
    } else if (code >= 400) {
        CLIENT_ERROR
    } else {
        UNEXPECTED_ERROR
    }
}