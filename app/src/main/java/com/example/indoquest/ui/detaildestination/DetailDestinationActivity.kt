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
import com.example.indoquest.ui.addcomment.AddCommentActivity
import com.example.indoquest.ui.detaildestination.fragment.DetailFragment
import com.example.indoquest.ui.home.HomeFragment
import com.example.indoquest.ui.maps.MapsActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailDestinationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailDestinationBinding
    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.des_tab_title_1,
            R.string.des_tab_title_2
        )
        val EXTRA_DESTINATION = "Destination"
        val INTENT_IMG_URL = "img_url"
        val INTENT_NAME = "name"
        val INTENT_LOCATION = "location"
    }

    @SuppressLint("MissingInflatedId", "CommitTransaction")
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

        val backBtn = binding.btnBack
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnCheckLocation = binding.btnCheckLocation
        btnCheckLocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
//            intent.putExtra(MapsActivity.EXTRA_LATITUDE, destination.latitude)
//            intent.putExtra(MapsActivity.EXTRA_LONGITUDE, destination.longitude)
            startActivity(intent)
            finish()
        }

        val fabAddComment = binding.btnAddComment
        fabAddComment.setOnClickListener {
            val intent = Intent(this, AddCommentActivity::class.java)
            intent.putExtra(INTENT_IMG_URL, destination.image)
            intent.putExtra(INTENT_NAME, destination.name)
            intent.putExtra(INTENT_LOCATION, destination.location)
            startActivity(intent)
            finish()
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