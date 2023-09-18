package com.example.cryptoapp.data.data_sourse

import com.example.cryptoapp.data.data_sourse.dto.CoinListDTO.CoinListDTO
import com.example.cryptoapp.data.data_sourse.dto.CoindDetailDTO.CoinDetailDTO
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    @GET("/api/v3/coins/list/markets?vs_currency=usd&order=market_cap_desc&per_page=100&sparkline=false")
    suspend fun getAllCoins(@Query("page")page:String):CoinListDTO

    @GET("/api/v3/coins/{id}")
    suspend fun getCoinByID(@Path("id")id:String): CoinDetailDTO

}