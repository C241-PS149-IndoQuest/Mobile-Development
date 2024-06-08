package com.example.indoquest.ui.detaildestination.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.indoquest.ui.detaildestination.fragment.DetailFragment
import com.example.indoquest.ui.detaildestination.fragment.RatingFragment

class DetailDestinationAdapter(activity: AppCompatActivity)
    : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = DetailFragment()
            1 -> fragment = RatingFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}