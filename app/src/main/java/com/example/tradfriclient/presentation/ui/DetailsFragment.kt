package com.example.tradfriclient.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tradfriclient.R
import com.example.tradfriclient.data.model.response.details.RoomDevicesResponseItem
import com.example.tradfriclient.data.util.Resource
import com.example.tradfriclient.databinding.FragmentDetailsBinding
import com.example.tradfriclient.presentation.MainActivity
import com.example.tradfriclient.presentation.adpater.DetailsAdapter
import com.example.tradfriclient.presentation.adpater.RoomsAdapter
import com.example.tradfriclient.presentation.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {
    companion object {
        const val TRANSITION_TIME = 1
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewmodel: DetailsViewModel
    private lateinit var detailsAdapter: DetailsAdapter
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        viewmodel = (activity as MainActivity).detailsViewModel
//        binding.deviceNameTv.text = args.selectedRoom.id.toString()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        detailsAdapter = DetailsAdapter()
        binding.devicesRv.apply {
            adapter = detailsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        getDevices()
        detailsAdapter.setOnClickListener {
            Log.e("MYTAG", "CLick from fragment")
            setDeviceState(it.id, it.state)
        }
        detailsAdapter.setOnSlideListener { roomDevicesResponseItem, sliderValue ->
            setDeviceDimAndTransition(roomDevicesResponseItem.id, sliderValue)
        }
        detailsAdapter.setOnColorClickListener { device, color ->
            setDeviceColor(device.id, color)
        }
    }

    private fun setDeviceColor(deviceId: Int, color: String) {
        viewmodel.setDeviceColor(deviceId, color)
    }

    private fun setDeviceDimAndTransition(deviceId: Int, sliderValue: Int) {
        viewmodel.setDeviceDimmerAndTransition(
            deviceId,
            sliderValue,
            TRANSITION_TIME,
            args.selectedRoom.id
        )
    }

    private fun setDeviceState(deviceId: Int, deviceState: Boolean) {
        viewmodel.setDeviceState(
            deviceId,
            deviceState = if (deviceState) 0 else 1,
            args.selectedRoom.id
        )
        viewmodel.stateList.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    Log.e("MYTAG2", response.message.toString())
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    Log.e("MYTAG", response.message.toString())
                }
            }
        })
    }

    private fun getDevices() {
        viewmodel.getRoomDevices(args.selectedRoom.id)
        viewmodel.devicesList.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    detailsAdapter.setList(response.data!!)
                    detailsAdapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                }
            }
        })
    }
}