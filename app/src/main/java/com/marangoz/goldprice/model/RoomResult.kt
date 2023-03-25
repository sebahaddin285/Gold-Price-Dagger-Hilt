package com.marangoz.goldprice.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_result")
data class RoomResult(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "pricestr") val pricestr: String,
    @ColumnInfo(name = "rate") val rate: Double,
    @ColumnInfo(name = "time") val time: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int? = null
)

