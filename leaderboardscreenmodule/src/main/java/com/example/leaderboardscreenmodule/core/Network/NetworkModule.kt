package com.example.leaderboardscreenmodule.core.Network

import com.example.leaderboardscreenmodule.core.Network.interceptor.ErrorInterseptor
import com.example.leaderboardscreenmodule.core.Network.interceptor.HeaderInterceptor
import com.example.leaderboardscreenmodule.integeration.SdkConfig
import com.example.leaderboardscreenmodule.integeration.SdkData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    val dummy=false

    lateinit var sdkData: SdkData
    var token: String? = ""
    var lang: String? = null
    var onErrorAction : ((ErrorEvent) ->Unit)? = null
    var errorInvoked : Boolean = false // means if there any error

    fun initializedata(sdkData: SdkData, language: SdkConfig) {
        NetworkModule.sdkData = sdkData
        token = sdkData.userToken
        lang = language.language.toString()
    }

    //one function for all the APIs services
    internal inline fun <reified T> provideApi(
        retrofit: Retrofit = if(dummy){
            provideRetrofit(provideOkHttpClient(HeaderInterceptor("en", null)))
        }else{provideRetrofit(
            provideOkHttpClient(
                if (sdkData?.userData != null) HeaderInterceptor(
                    lang ?: "en-US",
                    sdkData?.userData
                ) else HeaderInterceptor(lang ?: "en-US")
            )
        )
        }
    ):T {

        return retrofit!!.create(T::class.java)
    }


     internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            client(okHttpClient)
            sdkData?.baseURL?.let { baseUrl(it) }


        }.build()
    }

     internal fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(headerInterceptor)
            addInterceptor(provideLogInterceptor())
            addInterceptor(ErrorInterseptor{if(!errorInvoked){
                errorInvoked = true
                onErrorAction?.invoke(it)
            } })
        }.build()
    }

    private fun provideLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

}