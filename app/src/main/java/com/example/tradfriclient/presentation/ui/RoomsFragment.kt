package com.example.tradfriclient.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tradfriclient.R
import com.example.tradfriclient.data.util.Resource
import com.example.tradfriclient.databinding.FragmentRoomsBinding
import com.example.tradfriclient.presentation.MainActivity
import com.example.tradfriclient.presentation.adpater.RoomsAdapter
import com.example.tradfriclient.presentation.viewmodel.RoomsViewModel

class RoomsFragment : Fragment() {
    private lateinit var roomsAdapter: RoomsAdapter
    private lateinit var viewmodel: RoomsViewModel
    private lateinit var binding: FragmentRoomsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rooms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoomsBinding.bind(view)
        viewmodel = (activity as MainActivity).roomsViewModel
        initRecyclerView()
    }

    private fun initRecyclerView() {
        roomsAdapter = RoomsAdapter()
        binding.roomsRv.apply {
            adapter = roomsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        roomsAdapter.setOnClickListener {
            findNavController().navigate(RoomsFragmentDirections.actionRoomsFragmentToDetailsFragment(it))
        }
        getRooms()
    }

    private fun getRooms() {
        viewmodel.getRooms()
        viewmodel.roomsList.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    roomsAdapter.setList(response.data!!)
                    roomsAdapter.notifyDataSetChanged()
                    binding.progressCircular.isVisible = false
                }
                is Resource.Loading -> {
                    binding.progressCircular.isVisible = true
                }
                is Resource.Error -> {
                    binding.progressCircular.isVisible = false
                }
            }
        })
    }
}