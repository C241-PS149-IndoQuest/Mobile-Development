package com.example.indoquest.ui.explore

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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
    private val btnList = ArrayList<String>()
    private val destinationList = ArrayList<Destination>()

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

        addCategoryButton()

        buttonAdapter = ButtonAdapter(btnList) { buttonText ->
            when (buttonText) {
                "All" -> {
                    Toast.makeText(context, "Clicked: All", Toast.LENGTH_SHORT).show()
                }
                "BaselineSpa" -> Toast.makeText(context, "Clicked: BaselineSpa", Toast.LENGTH_SHORT).show()
                "Beach" -> Toast.makeText(context, "Clicked: Beach", Toast.LENGTH_SHORT).show()
                "Beachside" -> Toast.makeText(context, "Clicked: Beachside", Toast.LENGTH_SHORT).show()
                "CoffePlantation" -> Toast.makeText(context, "Clicked: CoffePlantation", Toast.LENGTH_SHORT).show()
                "Countryside" -> Toast.makeText(context, "Clicked: Countryside", Toast.LENGTH_SHORT).show()
                "DaySpa" -> Toast.makeText(context, "Clicked: DaySpa", Toast.LENGTH_SHORT).show()
                "HealthClub" -> Toast.makeText(context, "Clicked: HealthClub", Toast.LENGTH_SHORT).show()
                "HiddenBeach" -> Toast.makeText(context, "Clicked: HiddenBeach", Toast.LENGTH_SHORT).show()
                "Hiking" -> Toast.makeText(context, "Clicked: Hiking", Toast.LENGTH_SHORT).show()
                "HistoricalLandmark" -> Toast.makeText(context, "Clicked: HistoricalLandmark", Toast.LENGTH_SHORT).show()
                "HotSpring" -> Toast.makeText(context, "Clicked: HotSpring", Toast.LENGTH_SHORT).show()
                "LocalMarket" -> Toast.makeText(context, "Clicked: LocalMarket", Toast.LENGTH_SHORT).show()
                "LuxurySpa" -> Toast.makeText(context, "Clicked: LuxurySpa", Toast.LENGTH_SHORT).show()
                "Mountain" -> Toast.makeText(context, "Clicked: Mountain", Toast.LENGTH_SHORT).show()
                "PublicBeach" -> Toast.makeText(context, "Clicked: PublicBeach", Toast.LENGTH_SHORT).show()
                "RiceField" -> Toast.makeText(context, "Clicked: RiceField", Toast.LENGTH_SHORT).show()
                "SpiritualJourney" -> Toast.makeText(context, "Clicked: SpiritualJourney", Toast.LENGTH_SHORT).show()
                "Stays" -> Toast.makeText(context, "Clicked: Stays", Toast.LENGTH_SHORT).show()
                "Temple" -> Toast.makeText(context, "Clicked: Temple", Toast.LENGTH_SHORT).show()
                "TraditionalVillage" -> Toast.makeText(context, "Clicked: TraditionalVillage", Toast.LENGTH_SHORT).show()
                "ViewPoint" -> Toast.makeText(context, "Clicked: ViewPoint", Toast.LENGTH_SHORT).show()
                "WaterActivities" -> Toast.makeText(context, "Clicked: WaterActivities", Toast.LENGTH_SHORT).show()
                "WaterPalace" -> Toast.makeText(context, "Clicked: WaterPalace", Toast.LENGTH_SHORT).show()
                "WaterTemple" -> Toast.makeText(context, "Clicked: WaterTemple", Toast.LENGTH_SHORT).show()
                "Waterfall" -> Toast.makeText(context, "Clicked: Waterfall", Toast.LENGTH_SHORT).show()
                "WildLife" -> Toast.makeText(context, "Clicked: WildLife", Toast.LENGTH_SHORT).show()
            }
        }

        rvButton.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvButton.adapter = buttonAdapter

        destinationList.addAll(getListDestination())
        showRecyclerList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addCategoryButton(){
        btnList.add("All")
        btnList.add("BaselineSpa")
        btnList.add("Beach")
        btnList.add("Beachside")
        btnList.add("CoffePlantation")
        btnList.add("Countryside")
        btnList.add("DaySpa")
        btnList.add("HealthClub")
        btnList.add("HiddenBeach")
        btnList.add("Hiking")
        btnList.add("HistoricalLandmark")
        btnList.add("HotSpring")
        btnList.add("LocalMarket")
        btnList.add("LuxurySpa")
        btnList.add("Mountain")
        btnList.add("PublicBeach")
        btnList.add("RiceField")
        btnList.add("SpiritualJourney")
        btnList.add("Stays")
        btnList.add("Temple")
        btnList.add("TraditionalVillage")
        btnList.add("ViewPoint")
        btnList.add("WaterActivities")
        btnList.add("WaterPalace")
        btnList.add("WaterTemple")
        btnList.add("Waterfall")
        btnList.add("WildLife")
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
        rvDestination.layoutManager = GridLayoutManager(context, 2)
        val listDestinationAdapter = ListExploreAdapter(destinationList)
        rvDestination.adapter = listDestinationAdapter
    }
}