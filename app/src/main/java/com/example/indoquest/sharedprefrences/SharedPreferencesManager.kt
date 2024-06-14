package com.example.indoquest.sharedprefrences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context : Context) {
    companion object{
        val PREF_NAME = "MySharedPreferences"
        val TOKEN = "token"
        val USERNAME = "username"
        val NAME = "name"
        val EMAIL = "email"
    }

    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor : SharedPreferences.Editor = sharedPreferences.edit()

    fun saveData(name : String, email : String, token : String){
        editor.putString(USERNAME, name)
        editor.putString(NAME, name)
        editor.putString(EMAIL, email)
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getToken() : String?{
        return sharedPreferences.getString(TOKEN, null)
    }

    fun getName() : String?{
        return sharedPreferences.getString(NAME, null)
    }

    fun getUsername() : String?{
        return sharedPreferences.getString(USERNAME, null)
    }

    fun getEmail() : String?{
        return sharedPreferences.getString(EMAIL, null)
    }

    fun clearData(){
        editor.remove(TOKEN)
        editor.remove(NAME)
        editor.remove(EMAIL)
        editor.apply()
    }
}