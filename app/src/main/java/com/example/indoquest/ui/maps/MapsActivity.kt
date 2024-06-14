package com.example.indoquest.ui.maps

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.indoquest.R
import com.example.indoquest.databinding.ActivityMapsBinding
import com.example.indoquest.ui.detaildestination.DetailDestinationActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object{
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGITUDE = "extra_longitude"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)

        if (supportActionBar != null)
            supportActionBar?.hide()

        val rgMapType = binding.rgMapType
        rgMapType.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{
            group, checkedId ->
            when (checkedId) {
                R.id.rb_normal -> mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                R.id.rb_satelite -> mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                R.id.rb_terrain -> mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                R.id.rb_hybrid -> mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
        })

        val mapFragment = supportFragmentManager
            .findFragmentById(com.example.indoquest.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        addMarker(mMap)
        getMyLocation()

//        mMap.setOnMarkerClickListener {
//            val intent = Intent(this, DetailDestinationActivity::class.java)
//            intent.putExtra(EXTRA_LATITUDE, it.position.latitude)
//            intent.putExtra(EXTRA_LONGITUDE, it.position.longitude)
//            startActivity(intent)
//            finish()
//            true
//        }
    }

    private fun addMarker(gMap : GoogleMap){
        val mataram = LatLng(-8.5865678, 116.0850159)
        gMap.addMarker(MarkerOptions()
            .position(mataram)
            .title("Mataram City")
            .snippet("Kota Mataram, Nusa Tenggara Barat")
        )
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mataram, 15f))
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }
    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}