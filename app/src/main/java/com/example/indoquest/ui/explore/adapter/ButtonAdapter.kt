package com.example.indoquest.ui.explore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.indoquest.R

class ButtonAdapter(private val buttonList: List<String>, private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.btn_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val buttonText = buttonList[position]
        holder.button.text = buttonText
        holder.button.setOnClickListener {
            onClick(buttonText)
        }
    }

    override fun getItemCount(): Int {
        return buttonList.size
    }
}