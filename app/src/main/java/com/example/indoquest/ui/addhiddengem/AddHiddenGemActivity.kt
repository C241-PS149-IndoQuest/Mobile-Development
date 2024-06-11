package com.example.indoquest.ui.addhiddengem

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.indoquest.MainActivity
import com.example.indoquest.R
import com.example.indoquest.databinding.ActivityAddHiddenGemBinding
import com.example.indoquest.ui.hiddengem.HiddenGemFragment

class AddHiddenGemActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddHiddenGemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddHiddenGemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnBack = binding.ivBack
        val btnAddImg = binding.ivAddImg

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnAddImg.setOnClickListener {
            Toast.makeText(this, "Add Image", Toast.LENGTH_SHORT).show()
        }
    }
}