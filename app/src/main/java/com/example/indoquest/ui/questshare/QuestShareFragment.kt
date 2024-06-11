package com.example.indoquest.ui.questshare

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
import com.example.indoquest.databinding.FragmentHomeBinding
import com.example.indoquest.databinding.FragmentQuestShareBinding
import com.example.indoquest.model.Destination
import com.example.indoquest.model.UserPost
import com.example.indoquest.ui.addstory.AddStoryActivity
import com.example.indoquest.ui.home.HomeViewModel
import com.example.indoquest.ui.home.adapter.ListDestinationAdapter
import com.example.indoquest.ui.questshare.adapter.ListQuestShareAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class QuestShareFragment : Fragment() {

    private lateinit var rvUserPost: RecyclerView
    private val list = ArrayList<UserPost>()
    private lateinit var floatingActionButtonAdd: FloatingActionButton

    private var _binding: FragmentQuestShareBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestShareBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvUserPost = binding.rvQuestShare
        rvUserPost.setHasFixedSize(true)

        floatingActionButtonAdd = binding.fabAddPost

        list.addAll(getListUserPost())
        showRecyclerList()

        floatingActionButtonAdd.setOnClickListener {
            val intent = Intent(context, AddStoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    @SuppressLint("Recycle")
    private fun getListUserPost(): ArrayList<UserPost> {
        val dataName = resources.getStringArray(R.array.data_username_qs)
        val dataDescription = resources.getStringArray(R.array.data_desc_qs)
        val dataProfileImg = resources.obtainTypedArray(R.array.data_profile_qs)
        val dataLocation = resources.getStringArray(R.array.data_location_qs)
        val dataTitle = resources.getStringArray(R.array.data_title_qs)
        val dataPostimg = resources.obtainTypedArray(R.array.data_post_qs)

        val listDestination = ArrayList<UserPost>()
        for (i in dataName.indices) {
            val userPost = UserPost(dataName[i], dataLocation[i], dataProfileImg.getResourceId(i, -1), dataPostimg.getResourceId(i, -1), dataTitle[i], dataDescription[i])
            listDestination.add(userPost)
        }
        return listDestination
    }

    private fun showRecyclerList() {
        rvUserPost.layoutManager = LinearLayoutManager(context)
        val listQuestShareAdapter = ListQuestShareAdapter(list)
        rvUserPost.adapter = listQuestShareAdapter
    }
}