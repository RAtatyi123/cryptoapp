package com.example.cryptoapp.presentation.coin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.domain.use_case.CoinDetailUseCase
import com.example.cryptoapp.presentation.coinlist.CoinListState
import com.example.cryptoapp.utils.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val coinDetailUseCase: CoinDetailUseCase
) : ViewModel() {
    private val coinValue = MutableStateFlow(CoinState())
    var _coinValue : StateFlow<CoinState> = coinValue

    fun getCoinById(id: String) = viewModelScope.launch (Dispatchers.IO) {
        coinDetailUseCase(id).collect {
            when(it) {
                is ResponseState.Success -> {
                    coinValue.value = CoinState(coinDetail = it.data)
                }
                is ResponseState.Loading -> {
                    coinValue.value = CoinState(isLoading = true)
                }
                is ResponseState.Error -> {
                   coinValue.value = CoinState(error = it.message?: "An Expected Error")
                }
            }
        }
    }
}