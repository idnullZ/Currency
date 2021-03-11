package idnull.znz.currency.data.coinDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import idnull.znz.currency.domain.pojocoin.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {


        @Query("SELECT * FROM full_price_list ORDER BY lastUpdate DESC")
        fun getPriceList(): LiveData<List<CoinPriceInfo>>

        @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
        fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinPriceInfo>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertPriceList(priceList: List<CoinPriceInfo>)
}