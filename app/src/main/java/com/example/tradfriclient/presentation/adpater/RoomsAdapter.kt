package com.example.tradfriclient.presentation.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tradfriclient.R
import com.example.tradfriclient.data.model.response.rooms.RoomResponseItem
import com.example.tradfriclient.databinding.ItemGroupsBinding

class RoomsAdapter() : RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {
    private var roomsList = ArrayList<RoomResponseItem>()
    private val resourceIds = listOf(
        R.drawable.livingroom2,
        R.drawable.house,
        R.drawable.kitchen
    )

    fun setList(rooms: List<RoomResponseItem>) {
        roomsList.clear()
        roomsList.addAll(rooms)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemGroupsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_groups, parent, false)
        return RoomsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.bind(roomsList[position], resourceIds[position])
    }

    override fun getItemCount(): Int =
        roomsList.size


    inner class RoomsViewHolder(private val binding: ItemGroupsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(room: RoomResponseItem, roomIcon: Int) {
            if (room.id == 131073) {
                binding.groupNameTv.text = "House"
            } else {
                binding.groupNameTv.text = room.name
            }
            when (room.id) {
                131075 -> {
                    Glide.with(binding.groupIv.context)
                        .load(R.drawable.kitchen)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(binding.groupIv)
                }
                131076 -> {
                    Glide.with(binding.groupIv.context)
                        .load(R.drawable.livingroom2)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(binding.groupIv)
                }
                131073 -> {
                    Glide.with(binding.groupIv.context)
                        .load(R.drawable.house)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(binding.groupIv)
                }
                else -> null
            }
            binding.groupDevicesTv.text =
                String.format("Devices connected\n %s", room.deviceIds.size.toString())
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(room)
                }
            }
        }
    }

    private var onItemClickListener: ((RoomResponseItem) -> Unit)? = null

    fun setOnClickListener(listener: (RoomResponseItem) -> Unit) {
        onItemClickListener = listener
    }
}
