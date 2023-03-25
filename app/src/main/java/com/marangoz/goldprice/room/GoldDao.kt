package com.marangoz.goldprice.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.marangoz.goldprice.model.RoomResult

@Dao
interface GoldDao {

    @Query("select * from tbl_result")
    suspend fun allGold() : List<RoomResult>

    @Insert
    suspend fun insertResult(roomResult: RoomResult)



}