package com.example.cryptoapp.di

import com.example.cryptoapp.data.data_sourse.CoinGeckoApi
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.reppository.CoinRepository
import com.example.cryptoapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.invoke.ConstantCallSite
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoinGeckoModule {

@Provides
@Singleton
fun provideCoinGeckoApi() : CoinGeckoApi {
return Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(CoinGeckoApi::class.java)
}

    @Provides
    @Singleton
    fun provideCoinGeckoRepository(api:CoinGeckoApi):CoinRepository{
        return CoinRepositoryImpl(api)
    }

}