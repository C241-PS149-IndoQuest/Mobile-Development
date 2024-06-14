package com.example.indoquest.ui.addcomment

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.indoquest.MainActivity
import com.example.indoquest.databinding.ActivityAddCommentBinding
import com.example.indoquest.ui.detaildestination.DetailDestinationActivity
class AddCommentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ivDestination = binding.ivItemPhoto
        val tvName = binding.tvItemName
        val tvLocation = binding.tvItemLocation

        val imageUrl = intent.getIntExtra(DetailDestinationActivity.INTENT_IMG_URL, 0)
        ivDestination.setImageResource(imageUrl)
        tvName.text = intent.getStringExtra(DetailDestinationActivity.INTENT_NAME)
        tvLocation.text = intent.getStringExtra(DetailDestinationActivity.INTENT_LOCATION)

        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        val btnBack = binding.btnBack
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}