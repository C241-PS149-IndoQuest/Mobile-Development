package com.example.indoquest.ui.profile.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.indoquest.ui.profile.ProfileFragment
import com.example.indoquest.ui.profile.fragment.UserDestinationFragment
import com.example.indoquest.ui.profile.fragment.UserPostFragment

class DetailProfileAdapter(activity: ProfileFragment) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = UserPostFragment()
            1 -> fragment = UserDestinationFragment()
            2 -> fragment = UserPostFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 3
    }
}