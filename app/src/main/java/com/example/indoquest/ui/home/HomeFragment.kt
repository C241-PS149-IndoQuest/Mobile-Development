package com.example.indoquest.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.indoquest.R
import com.example.indoquest.databinding.FragmentHomeBinding
import com.example.indoquest.model.Destination
import com.example.indoquest.ui.home.adapter.ListDestinationAdapter

class HomeFragment : Fragment() {
    private lateinit var rvDestination: RecyclerView
    private val list = ArrayList<Destination>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvDestination = binding.rvRecommendDestination
        rvDestination.setHasFixedSize(true)

        list.addAll(getListDestination())
        showRecyclerList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("Recycle")
    private fun getListDestination(): ArrayList<Destination> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataRateString = resources.getStringArray(R.array.data_rate)
        val dataRate = dataRateString.map { it.toDouble() }
        val dataLocation = resources.getStringArray(R.array.data_location)
        val listDestination = ArrayList<Destination>()
        for (i in dataName.indices) {
            val destination = Destination(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataRate[i], dataLocation[i])
            listDestination.add(destination)
        }
        return listDestination
    }

    private fun showRecyclerList() {
        rvDestination.layoutManager = LinearLayoutManager(context)
        val listDestinationAdapter = ListDestinationAdapter(list)
        rvDestination.adapter = listDestinationAdapter
    }
}