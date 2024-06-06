package com.example.indoquest.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.indoquest.R
import com.example.indoquest.model.Destination
import com.example.indoquest.ui.detaildestination.DetailDestinationActivity
import com.example.indoquest.ui.detaildestination.fragment.DetailFragment


class ListDestinationAdapter(private val listDestination: ArrayList<Destination>) : RecyclerView.Adapter<ListDestinationAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_destination, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, image, rating, location) = listDestination[position]
        holder.imageDestination.setImageResource(image)
        holder.tvName.text = name
        holder.tvRate.text = rating.toString()
        holder.tvLocation.text = location
        holder.bind(listDestination[position])
    }

    override fun getItemCount(): Int = listDestination.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageDestination: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvRate : TextView = itemView.findViewById(R.id.tv_item_rating)
        val tvLocation : TextView = itemView.findViewById(R.id.tv_item_location)

        fun bind(destination : Destination){
            tvName.text = destination.name
            tvRate.text = destination.rating.toString()
            tvLocation.text = destination.location
            imageDestination.setImageResource(destination.image)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailDestinationActivity::class.java)
                intent.putExtra("Destination", destination)
                itemView.context.startActivity(intent)

//                val intent2 = Intent(itemView.context, DetailFragment::class.java)
//                intent2.putExtra("Destination", destination)
//                itemView.context.startActivity(intent2)
            }
        }
    }
}