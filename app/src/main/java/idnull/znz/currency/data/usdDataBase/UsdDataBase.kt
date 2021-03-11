package idnull.znz.currency.data.usdDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import idnull.znz.currency.domain.pojocoin.dataUsd.Rates


@Database(entities = [Rates::class],version = 1,exportSchema = false)
abstract class UsdDataBase:RoomDatabase() {

    abstract fun usdDao():UsdDao


}