package idnull.znz.currency.data.coinDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import idnull.znz.currency.domain.pojocoin.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class CoinDataBase : RoomDatabase() {

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}