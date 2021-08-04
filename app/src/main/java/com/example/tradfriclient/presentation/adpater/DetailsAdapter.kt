package com.example.tradfriclient.presentation.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tradfriclient.R
import com.example.tradfriclient.data.model.response.details.RoomDevicesResponseItem
import com.example.tradfriclient.databinding.ItemDeviceBinding
import com.google.android.material.slider.Slider

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    private var devicesList = ArrayList<RoomDevicesResponseItem>()

    fun setList(devices: List<RoomDevicesResponseItem>) {
        devicesList.clear()
        devicesList.addAll(devices)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDeviceBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_device, parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(devicesList[position])
    }

    override fun getItemCount(): Int =
        devicesList.size


    inner class DetailsViewHolder(private val binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(device: RoomDevicesResponseItem) {
            val colorsAdapter = ColorsAdapter()
            binding.colorsRv.apply {
                layoutManager = GridLayoutManager(this.context, 5)
                adapter = colorsAdapter
                setHasFixedSize(true)
                isLayoutFrozen = true
            }
            binding.deviceNameTv.text = device.name
            binding.deviceStateTv.text =
                String.format("Status\n %s", device.state)
            if (device.type == "light") {

                if (device.state) {
                    Glide.with(binding.deviceIv.context)
                        .load(R.drawable.ic_lightbulb_on)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(binding.deviceIv)
                    binding.brightnessSlider.value = ((device.dimmer * 100) / 254).toFloat()
                } else {
                    Glide.with(binding.deviceIv.context)
                        .load(R.drawable.ic_lightbulb_off)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(binding.deviceIv)
                    binding.brightnessSlider.value = 0f
                }
            } else {
                Glide.with(binding.deviceIv.context)
                    .load(R.drawable.ic_switch_on)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(binding.deviceIv)
                binding.brightnessSlider.isVisible = false
            }
            binding.deviceIv.setOnClickListener {
                onItemClickListener?.let {
                    Log.e("MYTAG", "CLICK ${device.id}")
                    it(device)
                }
            }
            binding.brightnessSlider.apply {
                addOnSliderTouchListener(object :
                    Slider.OnSliderTouchListener {
                    override fun onStartTrackingTouch(slider: Slider) {

                    }

                    override fun onStopTrackingTouch(slider: Slider) {
                        onItemSlideListener?.let {
                            it(device, slider.value.toInt())
                        }
                    }

                })
            }
            binding.colorsContainerLl.isVisible = !(device.id == 65537 || device.id == 65538)
            colorsAdapter.setOnClickListener { color ->
                onColorClickListener?.let {
                    it(device, color)
                }
            }
        }
    }

    private var onItemClickListener: ((RoomDevicesResponseItem) -> Unit)? = null
    fun setOnClickListener(listener: (RoomDevicesResponseItem) -> Unit) {
        onItemClickListener = listener
    }

    private var onItemSlideListener: ((RoomDevicesResponseItem, Int) -> Unit)? = null
    fun setOnSlideListener(listener: (RoomDevicesResponseItem, sliderValue: Int) -> Unit) {
        onItemSlideListener = listener
    }

    private var onColorClickListener: ((RoomDevicesResponseItem, String) -> Unit)? = null
    fun setOnColorClickListener(listener: (RoomDevicesResponseItem, String) -> Unit) {
        onColorClickListener = listener
    }
}