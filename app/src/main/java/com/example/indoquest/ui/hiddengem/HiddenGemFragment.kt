package com.example.indoquest.ui.hiddengem

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.indoquest.R
import com.example.indoquest.databinding.FragmentHiddenGemBinding
import com.example.indoquest.model.Destination
import com.example.indoquest.ui.addhiddengem.AddHiddenGemActivity
import com.example.indoquest.ui.hiddengem.adapter.ListHiddenGemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HiddenGemFragment : Fragment() {
    private var _binding: FragmentHiddenGemBinding? = null
    private lateinit var rvHiddenGem : RecyclerView
    private lateinit var floatingActionButtonAdd: FloatingActionButton

//    Seharusnya ini diganti sama HiddenGem
    private val list = ArrayList<Destination>()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHiddenGemBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvHiddenGem = binding.rvHiddenGem
        rvHiddenGem.setHasFixedSize(true)

        list.addAll(getListHiddenGem())
        showRecyclerList()

        floatingActionButtonAdd = binding.fabAddHiddengem
        floatingActionButtonAdd.setOnClickListener {
            val intent = Intent(activity, AddHiddenGemActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("Recycle")
    private fun getListHiddenGem(): ArrayList<Destination> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataRateString = resources.getStringArray(R.array.data_rate)
        val dataRate = dataRateString.map { it.toDouble() }
        val dataLocation = resources.getStringArray(R.array.data_location)
        val listDestination = ArrayList<Destination>()
        for (i in dataName.indices) {
            val hero = Destination(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataRate[i], dataLocation[i])
            listDestination.add(hero)
        }
        return listDestination
    }

    private fun showRecyclerList() {
        rvHiddenGem.layoutManager = LinearLayoutManager(context)
        val listDestinationAdapter = ListHiddenGemAdapter(list)
        rvHiddenGem.adapter = listDestinationAdapter
    }
}