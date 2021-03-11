package idnull.znz.currency.data.coinDataBase

import androidx.lifecycle.LiveData
import idnull.znz.currency.domain.pojocoin.CoinPriceInfo
import javax.inject.Inject

class CoinRepositoryRoom @Inject constructor(private val coinPriceInfoDao: CoinPriceInfoDao) {




    val getPriceList: LiveData<List<CoinPriceInfo>>
    get() =coinPriceInfoDao.getPriceList()

    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinPriceInfo>{
        return  coinPriceInfoDao.getPriceInfoAboutCoin(fSym)
    }

    fun insertPriceList(priceList: List<CoinPriceInfo>){
        coinPriceInfoDao.insertPriceList(priceList)
    }

}