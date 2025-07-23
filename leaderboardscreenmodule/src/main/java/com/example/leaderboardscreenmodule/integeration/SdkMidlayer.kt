package com.example.leaderboardscreenmodule.integeration

import android.content.Context
import android.content.Intent
import com.example.leaderboardscreenmodule.MainActivity
import com.example.leaderboardscreenmodule.core.Network.NetworkModule
import com.example.leaderboardscreenmodule.theme.ColorWhitelabel

 class SdkMidlayer(val sdkData: SdkData, val sdkConfig: SdkConfig, context: Context) {

    init {
        setColors()
        //setNetworkConfig()
    }

    fun start(context: Context){
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    fun setNetworkConfig(){
        NetworkModule.initializedata(sdkData, sdkConfig)
    }
    fun setColors(){
        ColorWhitelabel.apply {
            sdkConfig?.colors?.apply {
                active_color = activeColor
                main_color = mainColor
                second_text_color = secondTextColor
                main_text_color = mainTextColor
                variant_text_color = variantTextColor
                button_color = buttonColor
                error_color = errorColor
                success_color = successColor
                failing_image_color = failingImageColor
                failing_image_bg = failingImageBG
                color_scheme_is_dark = colorSchemeIsDark

            }
        }
    }
}