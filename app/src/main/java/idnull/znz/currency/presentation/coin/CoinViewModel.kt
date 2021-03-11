package idnull.znz.currency.presentation.coin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.znz.currency.data.coinDataBase.CoinRepositoryRoom
import idnull.znz.currency.data.api.ApiServiceCoin
import idnull.znz.currency.domain.pojocoin.CoinPriceInfo
import idnull.znz.currency.domain.pojocoin.CoinPriceInfoRawData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(private val  apiService: ApiServiceCoin,
                                        private val coinRepositoryRoom: CoinRepositoryRoom


) : ViewModel() {


    private val compositeDisposable = CompositeDisposable()
     val priceList = coinRepositoryRoom.getPriceList



    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return coinRepositoryRoom.getPriceInfoAboutCoin(fSym)
    }

    init {
        loadData()
    }



    private fun loadData() {

        val disposable = apiService.getTopCoinsInfo(limit = 50)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { apiService.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                coinRepositoryRoom.insertPriceList(it)
                //Log.d("TEST", "Success: $it")
            }, {
             //   Log.d("TEST", "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)

    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}