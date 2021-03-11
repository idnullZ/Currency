package idnull.znz.currency.presentation.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.znz.currency.data.api.ApiUsd
import idnull.znz.currency.data.usdDataBase.UsdRepositoryRoom
import idnull.znz.currency.domain.pojocoin.CoinPriceInfo
import idnull.znz.currency.domain.pojocoin.dataUsd.ItemRates
import idnull.znz.currency.domain.pojocoin.dataUsd.Rates
import idnull.znz.currency.domain.pojocoin.dataUsd.Respond
import idnull.znz.currency.utils.showToast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers

@HiltViewModel
class NotificationsViewModel @Inject constructor(private val usdRepositoryRoom: UsdRepositoryRoom) :
    ViewModel() {
    private val _ratesInfo = MutableLiveData<List<ItemRates>>()
    val ratesInfo: LiveData<List<ItemRates>> get() = _ratesInfo
    init {

        viewModelScope.launch {
                val itemListRates= usdRepositoryRoom.getUsd()
                val resultList =itemMade(itemListRates)
                _ratesInfo.postValue(resultList)
        }

    }


    private fun itemMade(rates: Rates?): List<ItemRates>{

        val result = ArrayList<ItemRates>()
        Rates::class.declaredMemberProperties.forEach {
            val item: ItemRates = ItemRates()


            try {
                item.name = it.name
                item.cost = it.call(rates).toString().toDouble()
                result.add(item)
            }catch (e:Exception){

                Log.d("TESTSUPERFICI"," ошибка в методе  $e")

            }


        }

        return result
    }


}