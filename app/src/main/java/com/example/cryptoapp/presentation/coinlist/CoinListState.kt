package com.example.cryptoapp.presentation.coinlist

import com.example.cryptoapp.domain.model.Coin
import com.example.cryptoapp.utils.ResponseState

data class CoinListState(
    val isLoading: Boolean = false,
    val coinList : List<Coin> = emptyList(),
    val error : String = ""
    )
