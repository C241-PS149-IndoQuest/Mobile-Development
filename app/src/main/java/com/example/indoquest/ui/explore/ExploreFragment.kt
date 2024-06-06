package com.example.indoquest.ui.explore

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.indoquest.R
import com.example.indoquest.databinding.FragmentExploreBinding
import com.example.indoquest.model.Destination
import com.example.indoquest.ui.explore.adapter.ButtonAdapter
import com.example.indoquest.ui.explore.adapter.ListExploreAdapter

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null

    private val binding get() = _binding!!

    private lateinit var rvButton : RecyclerView
    private lateinit var rvDestination : RecyclerView
    private lateinit var buttonAdapter: ButtonAdapter
    private val list = ArrayList<Destination>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvButton = binding.rvCategoryBtn
        rvDestination = binding.rvExploreDestination

        val buttonList = listOf("All", "BaselineSpa", "Beach", "Beachside", "CoffePlantation", "Countryside",
            "DaySpa", "HealthClub", "HiddenBeach", "Hiking", "HistoricalLandmark", "HotSpring", "LocalMarket", "LuxurySpa",
            "Mountain", "PublicBeach", "RiceField", "SpiritualJourney", "Stays", "Temple", "TraditionalVillage", "ViewPoint",
            "WaterActivities", "WaterPalace", "WaterTemple", "Waterfall", "WildLife"
        )

        buttonAdapter = ButtonAdapter(buttonList) { buttonText ->
            Toast.makeText(context, "Clicked: $buttonText", Toast.LENGTH_SHORT).show()
        }

        rvButton.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvButton.adapter = buttonAdapter

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
            val hero = Destination(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataRate[i], dataLocation[i])
            listDestination.add(hero)
        }
        return listDestination
    }

    private fun showRecyclerList() {
        rvDestination.layoutManager = LinearLayoutManager(context)
        val listDestinationAdapter = ListExploreAdapter(list)
        rvDestination.adapter = listDestinationAdapter
    }
}