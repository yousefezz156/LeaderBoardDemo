package com.example.leaderboardscreenmodule.core.Network

sealed class ErrorEvent {
    data object Init : ErrorEvent()
    data class Unauthorized(
        val message: String = "Token invalid please refresh token",
        val code: Int = 401,

        ) : ErrorEvent()

    data class NoInternetConnection(
        val message: String = "no internet connection",
        val code: Int = 500,
    ) : ErrorEvent()

    data class General(val code: Int, val message: String = "Something was wrong") : ErrorEvent()
}