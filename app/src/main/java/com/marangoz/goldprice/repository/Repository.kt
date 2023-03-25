package com.marangoz.goldprice.repository

import com.marangoz.goldprice.api.RetrofitService
import com.marangoz.goldprice.model.Gold
import com.marangoz.goldprice.model.RoomResult
import com.marangoz.goldprice.room.GoldDao
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(val api: RetrofitService,val goldDao: GoldDao) {

    suspend fun getGoldPrice(): Response<Gold> {

        return api.getGoldPrice()
    }

    suspend fun insertRoomResult(roomResult: RoomResult) {
        goldDao.insertResult(roomResult)
    }

    suspend fun getRoomdata() : List<RoomResult> = goldDao.allGold()

}