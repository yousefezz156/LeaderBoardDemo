package com.example.leaderboardscreenmodule.core.Network.interceptor

import com.example.leaderboardscreenmodule.core.Network.NetworkModule.token
import okhttp3.Interceptor
import okhttp3.Response

internal class HeaderInterceptor(private val lang: String, private val clientData: MutableMap<String, String>?) :
    Interceptor {
    internal constructor(lang: String) : this(lang, null)


    override fun intercept(chain: Interceptor.Chain): Response {

        val interceptorToken = if(token!=null) "Bearer $token" else "Bearer"
        val interceptorProcess =
            chain.request().newBuilder()
                .addHeader("Autherization", interceptorToken)
                .addHeader("Accept-Language", lang)


        clientData?.forEach { (key, value) ->
            interceptorProcess.addHeader(key,value)
        }

        return chain.proceed(interceptorProcess.build())
    }
}