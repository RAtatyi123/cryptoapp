package com.example.cryptoapp.domain.reppository

import com.example.cryptoapp.data.data_sourse.dto.CoinListDTO.CoinListDTO
import com.example.cryptoapp.data.data_sourse.dto.CoindDetailDTO.CoinDetailDTO

interface CoinRepository {

    suspend fun getAllCoins(page:String):CoinListDTO

    suspend fun getCoinById(id:String):CoinDetailDTO

}