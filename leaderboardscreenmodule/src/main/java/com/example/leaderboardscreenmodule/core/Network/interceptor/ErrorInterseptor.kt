package com.example.leaderboardscreenmodule.core.Network.interceptor

import com.example.leaderboardscreenmodule.core.Network.ErrorEvent
import com.example.leaderboardscreenmodule.core.Network.NetworkModule.errorInvoked
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterseptor(val onErrorAction : (ErrorEvent) ->Unit) :Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request() // will get the request for the http client
        val response = chain.proceed(request) // will process the request and return the response

        if(response.code.toString().startsWith("20")){
            // means the response return success
            errorInvoked = false
        }else if(response.code != 200){
            when(response.code){
                401 -> {onErrorAction.invoke(ErrorEvent.Unauthorized())}
                else ->{onErrorAction.invoke(ErrorEvent.General(response.code))}
            }
        }

        return response
    }
}