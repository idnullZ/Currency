package idnull.znz.currency.data.usdDataBase

import android.util.Log
import androidx.lifecycle.LiveData
import idnull.znz.currency.domain.pojocoin.dataUsd.Rates
import java.lang.Exception
import javax.inject.Inject

class UsdRepositoryRoom @Inject constructor(private val usdDao: UsdDao) {



   suspend fun getUsd():Rates = usdDao.getAllUsd()


    fun insertUsd(rates: Rates) {
        Log.d("TEST_USD", "2 смотрим что приходит к нам в репозиторий room  : $rates")

            usdDao.insertAllUsd(rates)

    }
}