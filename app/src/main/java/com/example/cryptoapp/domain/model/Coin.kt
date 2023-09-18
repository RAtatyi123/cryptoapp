package com.example.cryptoapp.domain.model

data class Coin(
    val id:String,
    val name:String,
    val image: String,
    val market_cap : Long,
    val  price : Double,
    val price_percentage_change: Double,
    val low_price : Double,
    val high_price : Double
)
