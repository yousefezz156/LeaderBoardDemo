package com.example.leaderboardscreenmodule.integeration

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.example.leaderboardscreenmodule.core.toString
import org.intellij.lang.annotations.Language
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.Date
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

val cId = "700ccb96-4d7f-4878-9846-3b1a3270d9a3"

data class SdkData(
    val userName: String,
    val baseURL: String,
    val appName: String,
    val userToken: String?,
    val userId: String?,
    val userData: MutableMap<String, String>?
) {
    constructor(userName: String, baseURL: String, userToken: String, appName: String) : this(
        userName, baseURL, appName, userToken = userToken, null, null
    )

    constructor(
        userName: String,
        baseURL: String,
        userId: String,
        appName: String,
        userData: MutableMap<String, String>?
    ) : this(
        userName, baseURL, appName, userToken = null, userId = userId, userData = userData
    )

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(
        userName: String,
        userId: String,
        appName: String,
    ) : this(
        userName,
        baseURL = "https://demo-gamification-api.dsquares.com/api/v1/Integration/",
        appName,
        userToken = null,
        userId = userId,
        userData = generateClientMap()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateClientMap(): MutableMap<String, String> {

    val timestamp = Date().toString("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    return mutableMapOf(
        "programGroupKey" to cId,
        "timeStamp" to timestamp,
        "signature" to generateHmacSignature(timeStamp = timestamp)

    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateHmacSignature(
    clientId: String = cId,
    timeStamp: String,
    clientSecret: String = "c8o5P2jFmO9oJp2jwv7kougqK8yuTv8WJLXApFcP1Xo="
): String {

    val message = clientId + timeStamp // why it's name message

    val secretKeySpec =
        SecretKeySpec(clientSecret.toByteArray(StandardCharsets.UTF_8), "HmacSHA256")
    val mac = Mac.getInstance("HmacSHA256")
    mac.init(secretKeySpec)

    val hashBytes = mac.doFinal(message.toByteArray(StandardCharsets.UTF_8))
    val signature = Base64.getEncoder().encodeToString(hashBytes)

    return signature

}

data class SdkConfig(
    val language: Language,
    val colors: SdkColors,
)

data class SdkColors(
    val activeColor: Color,
    val mainColor: Color,
    val secondTextColor: Color,
    val mainTextColor: Color,
    val variantTextColor: Color,
    val errorColor: Color,
    val successColor: Color,
    val buttonColor: Color,
    val failingImageColor: Color,
    val failingImageBG: Color,
    val colorSchemeIsDark: Boolean,
)
