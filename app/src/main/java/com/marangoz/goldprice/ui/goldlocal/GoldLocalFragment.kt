package com.marangoz.goldprice.ui.goldlocal

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marangoz.goldprice.R
import com.marangoz.goldprice.adapter.GoldAdapter
import com.marangoz.goldprice.databinding.FragmentGoldBinding
import com.marangoz.goldprice.databinding.FragmentGoldLocalBinding
import com.marangoz.goldprice.model.RoomResult
import com.marangoz.goldprice.ui.goldnetwork.GoldFragmentViewModel
import com.marangoz.goldprice.utils.Util
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GoldLocalFragment : Fragment() {


    private lateinit var binding: FragmentGoldLocalBinding
    private val viewModel by viewModels<GoldLocalFragmentViewModel>()
    val adap: GoldAdapter by lazy { GoldAdapter(requireActivity()) { data -> insertData(data) } }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoldLocalBinding.inflate(inflater, container, false)

        binding.rvRegister.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adap
        }
        if (Util.isNetworkAvailable(requireContext())){
            viewModel.getRoomData()

            viewModel.goldRoomList.observe(viewLifecycleOwner) { room ->
                if (room.isNotEmpty() ){ adap.setResultList(room)}

            }
        }else{
            Toast.makeText(requireContext(),"İnternet Bağlantısınız Kotrol ediniz", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

    fun insertData(data: RoomResult) {}





}