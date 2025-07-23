package com.example.leaderboardscreenmodule.core

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

internal fun String.getStringDate(
    initialFormat: String = "yyyy-MM-dd'T'hh:mm:ss",
    requiredFormat: String,
    locale: Locale = Locale.getDefault(),
    timeZoned: Boolean = true,
): String {
    return this.toDate(initialFormat, locale, timeZoned).toString(requiredFormat, locale, false)
}

internal fun Number.localize(): String =
    NumberFormat.getInstance(Locale.getDefault()).format(this).replace("٬", ",")
internal fun String.toDate(
    format: String,
    locale: Locale = Locale.getDefault(),
    timeZoned: Boolean = true,
): Date {
    val formatter = SimpleDateFormat(format, locale)
    if (timeZoned)
        formatter.timeZone = TimeZone.getTimeZone("GMT")
    return formatter.parse(this)!!
}

internal fun Date.toString(
    format: String,
    locale: Locale = Locale.getDefault(),
    timeZoned: Boolean = true,
): String {
    val formatter = SimpleDateFormat(format, locale)
    if (timeZoned)
        formatter.timeZone = TimeZone.getTimeZone("GMT")
    return formatter.format(this)
}