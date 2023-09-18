package com.example.cryptoapp.presentation.coinlist


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.domain.model.Coin
import com.example.cryptoapp.domain.use_case.CoinListUseCase
import com.example.cryptoapp.utils.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject

class CoinListViewModel @Inject constructor(
    private val coinsUseCase: CoinListUseCase
) : ViewModel() {
    private val CoinListValue  = MutableStateFlow(CoinListState())
    var _coinListValue : StateFlow<CoinListState> = CoinListValue

    fun getAllCoins(page: String) = viewModelScope.launch(Dispatchers.IO) {
        coinsUseCase(page).collect {
            when(it) {
                is ResponseState.Success -> {
                    CoinListValue.value = CoinListState(coinList = it.data?: emptyList())
                }
                is ResponseState.Loading -> {
                    CoinListValue.value = CoinListState(isLoading = true)
                }
                is ResponseState.Error -> {
                    CoinListValue.value = CoinListState(error = it.message?: "An Expected Error")
                }
            }
        }
    }
}