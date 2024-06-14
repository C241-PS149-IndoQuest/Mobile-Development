package com.example.indoquest.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.indoquest.MainActivity
import com.example.indoquest.R
import com.example.indoquest.sharedprefrences.SharedPreferencesManager
import com.example.indoquest.ui.onboarding.OnBoardingActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        sharedPreferencesManager = SharedPreferencesManager(this)
        Log.d("TAG", sharedPreferencesManager.getToken().toString())
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

        // Hide the action bar
        if (supportActionBar != null) {
            supportActionBar?.hide();
        }

        Handler(Looper.getMainLooper()).postDelayed({
            checkTokenExist()
        }, 3000)
    }

    private fun checkTokenExist(){
        if(sharedPreferencesManager.getToken() == null){
            Log.d("TAG", "No Token")
            val intent = Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }else{
            Log.d("TAG", sharedPreferencesManager.getToken()!!)
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}