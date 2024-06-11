package com.example.indoquest.ui.questshare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.indoquest.R
import com.example.indoquest.model.UserPost

class ListQuestShareAdapter(private val listQuestSharePost: ArrayList<UserPost>) : RecyclerView.Adapter<ListQuestShareAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_questshare, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, location, profile_img, post_img, title, description) = listQuestSharePost[position]
        holder.tvUsername.text = username
        holder.tvLocation.text = location
        holder.imgProfile.setImageResource(profile_img)
        holder.imgPost.setImageResource(post_img)
        holder.tvTitle.text = title
        holder.tvDescription.text = description
    }

    override fun getItemCount(): Int = listQuestSharePost.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername : TextView = itemView.findViewById(R.id.tv_username)
        val tvLocation : TextView = itemView.findViewById(R.id.tv_location)
        val imgProfile : ImageView = itemView.findViewById(R.id.iv_image_profile)
        val imgPost : ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvTitle : TextView = itemView.findViewById(R.id.tv_title)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_description)
    }
}