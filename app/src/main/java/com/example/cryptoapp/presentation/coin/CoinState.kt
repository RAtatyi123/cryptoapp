package com.example.cryptoapp.presentation.coin

import com.example.cryptoapp.domain.model.Coin
import com.example.cryptoapp.domain.model.CoinDetail

data class CoinState(
    val isLoading: Boolean = false,
    val coinDetail : CoinDetail? = null,
    val error : String = ""
)
