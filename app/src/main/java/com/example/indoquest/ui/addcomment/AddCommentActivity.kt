package com.example.indoquest.ui.addcomment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.indoquest.R
import com.example.indoquest.databinding.ActivityAddCommentBinding
import com.example.indoquest.databinding.ActivityMainBinding

class AddCommentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }
    }
}