package com.example.cryptoapp.data.repository

import com.example.cryptoapp.data.data_sourse.CoinGeckoApi
import com.example.cryptoapp.data.data_sourse.dto.CoinListDTO.CoinListDTO
import com.example.cryptoapp.data.data_sourse.dto.CoindDetailDTO.CoinDetailDTO
import com.example.cryptoapp.domain.reppository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinGeckoApi
): CoinRepository {
    override suspend fun getAllCoins(page: String): CoinListDTO {
        return coinApi.getAllCoins(page=page)
    }

    override suspend fun getCoinById(id: String): CoinDetailDTO {
        return coinApi.getCoinByID(id = id)
    }


}