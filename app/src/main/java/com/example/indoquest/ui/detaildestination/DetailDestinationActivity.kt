package com.example.indoquest.ui.detaildestination

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.indoquest.MainActivity
import com.example.indoquest.R
import com.example.indoquest.ui.detaildestination.adapter.DetailDestinationAdapter
import com.example.indoquest.databinding.ActivityDetailDestinationBinding
import com.example.indoquest.model.Destination
import com.example.indoquest.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailDestinationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailDestinationBinding

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.tab_title_1,
            R.string.tab_title_2
        )
        val EXTRA_DESTINATION = "Destination"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }

        // Retrieve the Parcelable object from the Intent
        val destination = intent.getParcelableExtra<Destination>(EXTRA_DESTINATION) as Destination
        setupData(destination)

        // Now, pass this data to the fragment
//        if (destination != null) {
//
//        }

        val backBtn = binding.btnBack
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val sectionsPagerAdapter = DetailDestinationAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun setupData(destination : Destination){
        val tvItemName = binding.tvItemName
        val ivItemPhoto = binding.ivItemPhoto
        val tvItemLocation = binding.tvItemLocation
        val tvItemRating = binding.tvItemRating

        tvItemName.text = destination.name
        tvItemLocation.text = destination.location
        tvItemRating.text = destination.rating.toString()
        ivItemPhoto.setImageResource(destination.image)
    }
}