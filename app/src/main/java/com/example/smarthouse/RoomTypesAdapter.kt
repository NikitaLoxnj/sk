package com.example.smarthouse

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class RoomTypesAdapter(private val rooms:List<RoomType>,
    private val onRoomTypeSelected: (Set<RoomType>)->Unit) : RecyclerView.Adapter<RoomTypesAdapter.RoomViewHolder>(){
    private val selectedItems = mutableSetOf<Int>()
    class RoomViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val roomImageView : ImageView = itemView.findViewById(R.id.roomIcon)
        val roomNameTextView : TextView = itemView.findViewById(R.id.roomName)
        val roomBack  = itemView.findViewById<ConstraintLayout>(R.id.back)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_room,parent,false)
        return RoomViewHolder(view)
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = rooms[position]
        holder.roomNameTextView.text = room.typename
        GlideApp.with(holder.itemView.context).load(room.icon).into(holder.roomImageView)
        Log.d("ImageLoader", "Loading image from URL: $room.iconUrl")

        val isSelected = selectedItems.contains(position)


        holder.roomBack.setBackgroundResource(
            if (isSelected) R.drawable.add_item else R.drawable.add_item_two
        )
        holder.roomNameTextView.setTextColor(
            if (isSelected) Color.parseColor("#0B50A0") else Color.parseColor("#94949B")
        )


        holder.itemView.setOnClickListener {
            if (isSelected) {
                selectedItems.remove(position)
            } else {
                selectedItems.add(position)
            }


            notifyItemChanged(position)

            val selectedRooms = selectedItems.map { rooms[it] }.toSet()
            onRoomTypeSelected(selectedRooms)
        }
        }
    }


