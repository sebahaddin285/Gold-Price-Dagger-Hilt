package com.marangoz.goldprice.ui.goldnetwork

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
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
import com.marangoz.goldprice.model.RoomResult
import com.marangoz.goldprice.utils.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoldFragment : Fragment() {

    private lateinit var binding: FragmentGoldBinding
    private val viewModel by viewModels<GoldFragmentViewModel>()
    private lateinit var alertDialog : AlertDialog
    val adap: GoldAdapter by lazy { GoldAdapter(requireActivity()){ data -> insertData(data) } }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoldBinding.inflate(inflater, container, false)
        alertDialogStatus(true)
        binding.rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adap
        }
        if (Util.isNetworkAvailable(requireContext())){
            viewModel.getGoldPrice()

            viewModel.goldList.observe(viewLifecycleOwner) { response ->
                if (response.isSuccessful ){
                    alertDialogStatus(false)
                    response.body()?.result?.let { adap.setList(it) }
                }else{
                    alertDialogStatus(false)
                    Toast.makeText(requireContext(),"Bir Hata Oluştur",Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            alertDialogStatus(false)
            Toast.makeText(requireContext(),"İnternet Bağlantısınız Kotrol ediniz",Toast.LENGTH_SHORT).show()
        }




        return binding.root
    }


    @SuppressLint("InflateParams")
    private fun alertDialogStatus(state: Boolean) {
        if (state) {
            val builder = AlertDialog.Builder(requireContext())
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.custom_dialog_loading, null))
            builder.setCancelable(false)
            alertDialog = builder.create()
            alertDialog.show()
        } else {
            alertDialog.dismiss()
        }
    }



    fun insertData(data : RoomResult){
        viewModel.insertResult(data)
    }



}