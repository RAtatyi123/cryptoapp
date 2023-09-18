package com.example.cryptoapp.domain.use_case

import com.example.cryptoapp.domain.model.Coin
import com.example.cryptoapp.domain.model.CoinDetail
import com.example.cryptoapp.domain.reppository.CoinRepository
import com.example.cryptoapp.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor (
    private val repository: CoinRepository
) {
    operator fun invoke(id:String): Flow<ResponseState<CoinDetail>> = flow {

        try {
            emit(ResponseState.Loading<CoinDetail>())
            val coinDetail = repository.getCoinById(id).toCoinDetail()
            emit(ResponseState.Success<CoinDetail>(coinDetail))
        }
        catch (e: HttpException) {
            emit(ResponseState.Error<CoinDetail>(e.localizedMessage ?:
            " An Unexpected Error Occurred"))
        }
        catch (e: IOException){
            emit(ResponseState.Error<CoinDetail>(message = "Error Occurred"))
        }
    }
}
