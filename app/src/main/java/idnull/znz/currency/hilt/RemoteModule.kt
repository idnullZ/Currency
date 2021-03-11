package idnull.znz.currency.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import idnull.znz.currency.data.api.ApiServiceCoin
import idnull.znz.currency.data.api.ApiUsd

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class RemoteModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoinRetrofit


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsdRetrofit


    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        return okHttpClient
    }

    @Provides
    @CoinRetrofit
    @Singleton
    fun provideCoinRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val BASE_URL = "https://min-api.cryptocompare.com/data/"
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit
    }
    @Provides
    @Singleton
    fun provideCoinApi(@CoinRetrofit retrofit: Retrofit):ApiServiceCoin  = retrofit.create(ApiServiceCoin::class.java)

    @Provides
    @Singleton
    fun provideApiUsd(@UsdRetrofit retrofit: Retrofit):ApiUsd  = retrofit.create(ApiUsd::class.java)



    @UsdRetrofit
    @Provides
    @Singleton
    fun provideCurrencyRetrofit():Retrofit{
        val BASE_URL = "http://data.fixer.io/api/"
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit
    }









    }


