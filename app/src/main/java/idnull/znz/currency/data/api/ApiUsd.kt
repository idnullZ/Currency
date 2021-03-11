package idnull.znz.currency.data.api

import idnull.znz.currency.domain.pojocoin.dataUsd.Respond
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUsd {

    @GET("latest")
    fun getRespond(
            @Query(ApiUsd.QUERY_PARAM_ACCESS_KEY) access_key: String = "7872ba80753c40a73a789b6b6d15c7d9"

    ): Single<Respond>

    companion object {
        private const val QUERY_PARAM_ACCESS_KEY = "access_key"

    }




}