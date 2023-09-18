package com.example.cryptoapp.domain.model

import android.app.ActivityManager.TaskDescription

data class CoinDetail(
    val name: String,
    val image: String,
    val market_cap : Double,
    val price : Double,
    val price_percentage_change: Double,
    val low_price: Double,
    val high_price: Double,
    val description: String
)
