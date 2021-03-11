package idnull.znz.currency.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.znz.currency.data.api.ApiUsd
import idnull.znz.currency.data.usdDataBase.UsdRepositoryRoom
import idnull.znz.currency.domain.pojocoin.dataUsd.Rates
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
     private val apiUsd: ApiUsd,
     private val usdRepositoryRoom: UsdRepositoryRoom
) : ViewModel() {
    private val _info = MutableLiveData<Rates>()
    val usdInfo: LiveData<Rates> get() = _info





    private val compositeDisposable = CompositeDisposable()

    init {

        val disposable = apiUsd.getRespond()
            .subscribeOn(Schedulers.io())
            .subscribe({

                it.rates?.let { it1 ->
                    usdRepositoryRoom.insertUsd(it1)
                } },
                 {
                      Log.d("TEST", "Failure: ${it.message}")
                 })

        compositeDisposable.add(disposable)
            viewModelScope.launch {
                _info.value = usdRepositoryRoom.getUsd()
            }
    }




    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}