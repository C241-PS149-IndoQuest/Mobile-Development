package com.example.indoquest.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.indoquest.R
import com.example.indoquest.databinding.FragmentProfileBinding
import com.example.indoquest.sharedprefrences.SharedPreferencesManager
import com.example.indoquest.ui.editprofile.EditProfileActivity
import com.example.indoquest.ui.profile.adapter.DetailProfileAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {
    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.user_tab_title_1,
            R.string.user_tab_title_2,
            R.string.user_tab_title_3
        )
    }

    private var _binding: FragmentProfileBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferencesManager = SharedPreferencesManager(requireContext())

        val sectionsPagerAdapter = DetailProfileAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

//        val tvName = binding.tvUsername
//        val tvEmail = binding.tvEmail
//        tvName.text = "${sharedPreferencesManager.getName()}"
//        tvEmail.text = "${sharedPreferencesManager.getEmail()}@gmail.com"

        val btnEditProfile = binding.btnEditProfile

        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        btnEditProfile.setOnClickListener {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}