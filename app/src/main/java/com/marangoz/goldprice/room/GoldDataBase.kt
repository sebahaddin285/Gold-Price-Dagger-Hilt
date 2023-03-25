package com.marangoz.goldprice.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marangoz.goldprice.model.RoomResult

@Database(entities = [RoomResult::class], version = 1)
abstract class GoldDataBase : RoomDatabase() {

    abstract fun goldDao(): GoldDao

    companion object {
        const val DATABASE_NAME: String = "gold_db"
    }


}