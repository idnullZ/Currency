package idnull.znz.currency.data.usdDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import idnull.znz.currency.domain.pojocoin.dataUsd.Rates


@Dao
interface UsdDao {

    @Query("SELECT * from usd_database")
   suspend fun getAllUsd():Rates



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsd(rates: Rates)
}