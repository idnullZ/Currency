package idnull.znz.currency.hilt

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import idnull.znz.currency.data.coinDataBase.CoinDataBase
import idnull.znz.currency.data.coinDataBase.CoinPriceInfoDao
import idnull.znz.currency.data.usdDataBase.UsdDao
import idnull.znz.currency.data.usdDataBase.UsdDataBase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideCoinDao(coinDataBase: CoinDataBase): CoinPriceInfoDao =
            coinDataBase.coinPriceInfoDao()


    @Singleton
    @Provides
    fun provideCoinDataBase(@ApplicationContext app:Context)=
            Room.databaseBuilder(
                    app,
                    CoinDataBase::class.java,
                    "coin.db"
            ).build()






    @Singleton
    @Provides
    fun provideUsdDataBase(@ApplicationContext app:Context)=
            Room.databaseBuilder(
                    app,
                    UsdDataBase::class.java,
                    "usd.db"
            ).build()



    @Singleton
    @Provides
    fun provideUsdDao(usdDataBase: UsdDataBase ): UsdDao =
        usdDataBase.usdDao()



}