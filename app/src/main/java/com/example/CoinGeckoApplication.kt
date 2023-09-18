package com.example

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.GET

@HiltAndroidApp
class CoinGeckoApplication:Application()
