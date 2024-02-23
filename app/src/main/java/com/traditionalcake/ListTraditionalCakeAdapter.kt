package com.traditionalcake

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListTraditionalCakeAdapter(private val listTraditionalCake: ArrayList<TraditionalCake>) :
    RecyclerView.Adapter<ListTraditionalCakeAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_cake, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, daerah, photo) = listTraditionalCake[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listTraditionalCake[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listTraditionalCake.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name_cake)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description_cake)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TraditionalCake)
    }
}

