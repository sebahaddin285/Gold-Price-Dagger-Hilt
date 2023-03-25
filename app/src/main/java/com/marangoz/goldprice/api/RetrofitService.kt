package com.marangoz.goldprice.api

import com.marangoz.goldprice.model.Gold
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {


    @Headers(
        "content-type: application/json",
        "authorization: apikey 6BDdCQeLqict9tbQVcdf7j:2mtY89iPsFQyoGgvpjMc85"
    )

    @GET("/economy/liveBorsa")
    suspend fun getGoldPrice(): Response<Gold>


}