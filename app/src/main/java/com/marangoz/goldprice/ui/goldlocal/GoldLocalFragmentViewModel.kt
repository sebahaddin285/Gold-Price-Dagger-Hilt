package com.marangoz.goldprice.ui.goldlocal

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
class GoldLocalFragmentViewModel @Inject constructor(val repo: Repository) : ViewModel() {

    val goldRoomList: MutableLiveData<List<RoomResult>> = MutableLiveData()
    fun getRoomData()  {
        viewModelScope.launch {
            goldRoomList.value = repo.getRoomdata()
        }
    }

}