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

    var dummy: Boolean=false

    lateinit var sdkData: SdkData
    var token: String? = ""
    var lang: String? = null
    var onErrorAction : ((ErrorEvent) ->Unit)? = null
    var errorInvoked : Boolean = false // means if there any error

    fun initializedata(sdkData: SdkData, language: SdkConfig) {
        android.util.Log.d("NetworkModule", "Initializing data with base URL: ${sdkData.baseURL}")
        NetworkModule.sdkData = sdkData
        token = sdkData.userToken
        lang = language.language.toString()
        android.util.Log.d("NetworkModule", "Data initialized - token: $token, lang: $lang, dummy: $dummy")
    }

    //one function for all the APIs services
    internal inline fun <reified T> provideApi(
        retrofit: Retrofit = if(dummy){
            // When dummy is true, use a mock base URL that won't actually make network calls
            android.util.Log.d("NetworkModule", "Using dummy mode with mock API")
            provideRetrofit(provideOkHttpClient(), "https://mock.api/")
        }else{
            android.util.Log.d("NetworkModule", "Using real API mode")
            provideRetrofit(
                provideOkHttpClient()
            )
        }
    ):T {
        android.util.Log.d("NetworkModule", "Creating API service for ${T::class.java.simpleName}")
        return retrofit!!.create(T::class.java)
    }


     internal fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String? = null): Retrofit {
        val finalBaseUrl = if (baseUrl != null) {
            android.util.Log.d("NetworkModule", "Using provided base URL: $baseUrl")
            baseUrl
        } else {
            val sdkBaseUrl = sdkData?.baseURL
            android.util.Log.d("NetworkModule", "Using SDK base URL: $sdkBaseUrl")
            sdkBaseUrl
        }
        
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            client(okHttpClient)
            if (finalBaseUrl != null) {
                baseUrl(finalBaseUrl)
            }
        }.build()
    }

     internal fun provideOkHttpClient(/*headerInterceptor: HeaderInterceptor*/): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(HeaderInterceptor())
            //addInterceptor(headerInterceptor)
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