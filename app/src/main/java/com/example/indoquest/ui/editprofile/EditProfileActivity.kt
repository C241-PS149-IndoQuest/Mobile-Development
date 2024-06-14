package com.example.indoquest.ui.editprofile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.indoquest.R
import com.example.indoquest.databinding.ActivityEditProfileBinding
import com.example.indoquest.sharedprefrences.SharedPreferencesManager
import com.example.indoquest.ui.login.LoginActivity
import com.example.indoquest.ui.signup.SignUpActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditProfileBinding
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferencesManager = SharedPreferencesManager(this)

        val btnLogout = binding.btnLogout

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }

        btnLogout.setOnClickListener {
            sharedPreferencesManager.clearData()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}