package com.example.indoquest.ui.detaildestination.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.indoquest.R

class DetailFragment : Fragment() {

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        private const val EXTRA_DESTINATION = "Destination"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}