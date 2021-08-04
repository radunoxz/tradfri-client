package com.example.tradfriclient.presentation.adpater

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tradfriclient.R
import com.example.tradfriclient.data.model.response.details.RoomDevicesResponseItem
import com.example.tradfriclient.databinding.ItemColorsBinding


class ColorsAdapter : RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder>() {

//    private val colorsList = listOf(
//        R.color.blue,
//        R.color.light_blue,
//        R.color.saturated_purple,
//        R.color.lime,
//        R.color.light_purple,
//        R.color.yellow,
//        R.color.saturated_pink,
//        R.color.dark_peach,
//        R.color.saturated_red,
//        R.color.cold_sky,
//        R.color.pink,
//        R.color.peach,
//        R.color.warm_amber,
//        R.color.light_pink,
//        R.color.cool_daylight,
//        R.color.candle_light,
//        R.color.warm_glow,
//        R.color.warm_white,
//        R.color.sunrise,
//        R.color.cool_white
//    )

    private val colorsIdsList = listOf(
        "ebb63e",
        "efd275",
        "f1e0b5",
        "f2eccf",
        "f5faf6",
        "e78834",
        "e491af",
        "e8bedd",
        "d6e44b",
        "eaf6fb",
        "e57345",
        "d9337c",
        "c984bb",
        "a9d62b",
        "dcf0f8",
        "da5d41",
        "dc4b31",
        "8f2686",
        "4a418a",
        "6c83ba"
    )

    private val colorsList = listOf(
        R.drawable.background_circle16,
        R.drawable.background_circle17,
        R.drawable.background_circle18,
        R.drawable.background_circle19,
        R.drawable.background_circle20,
        R.drawable.background_circle13,
        R.drawable.background_circle11,
        R.drawable.background_circle14,
        R.drawable.background_circle6,
        R.drawable.background_circle15,
        R.drawable.background_circle12,
        R.drawable.background_circle7,
        R.drawable.background_circle5,
        R.drawable.background_circle4,
        R.drawable.background_circle10,
        R.drawable.background_circle8,
        R.drawable.background_circle9,
        R.drawable.background_circle3,
        R.drawable.background_circle,
        R.drawable.background_circle2

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemColorsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_colors, parent, false)
        return ColorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        holder.bind(colorsList[position], colorsIdsList[position])
    }

    override fun getItemCount(): Int =
        colorsList.size


    inner class ColorsViewHolder(private val binding: ItemColorsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(color: Int, colorHex: String) {
            Glide.with(binding.colorBtn.context)
                .load(color)
                .into(binding.colorBtn)
            binding.colorBtn.setOnClickListener {
                onItemClickListener?.let {
                    it(colorHex)
                }
            }
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null
    fun setOnClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}