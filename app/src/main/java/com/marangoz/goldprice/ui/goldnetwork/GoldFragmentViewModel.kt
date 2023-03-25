package com.marangoz.goldprice.ui.goldnetwork

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marangoz.goldprice.model.Gold
import com.marangoz.goldprice.model.RoomResult
import com.marangoz.goldprice.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class GoldFragmentViewModel @Inject constructor(val repo: Repository) : ViewModel() {

    val goldList: MutableLiveData<Response<Gold>> = MutableLiveData()


    fun getGoldPrice() {
        viewModelScope.launch {
            goldList.value = repo.getGoldPrice()
        }

    }

    fun insertResult(roomResult: RoomResult) {
        viewModelScope.launch {
            repo.insertRoomResult(roomResult)
        }

    }


}