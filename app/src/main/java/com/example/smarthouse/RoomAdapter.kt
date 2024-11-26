package com.example.smarthouse

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RoomAdapter  (private val room:List<Rooms>) : RecyclerView.Adapter<RoomAdapter.RoomsViewHolder>() {
    class RoomsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomsImage: ImageView = itemView.findViewById(R.id.imgRoom)
        val roomNameTextView: TextView = itemView.findViewById(R.id.namRoom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.roomget_item,parent,false)
        return RoomAdapter.RoomsViewHolder(view)
    }

    override fun getItemCount(): Int = room.size

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        val room = room[position]
        val roomtype : RoomType? = null
        holder.roomNameTextView.text = room.name
        GlideApp.with(holder.itemView.context).load(roomtype?.icon).into(holder.roomsImage)
        Log.d("ImageLoader", "Loading image from URL: $room.iconUrl")
    }


}