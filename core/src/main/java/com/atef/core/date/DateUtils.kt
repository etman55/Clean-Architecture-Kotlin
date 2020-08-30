package com.atef.core.date

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

private const val DATE_ISO_8601_FORMAT = "yyyy-MM-dd'T'hh:mm:ss"

fun String.getDate(): Date? {
    return if (this.isDateInISO8601()) {
        val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        format.parse(this)
    } else {
        null
    }
}

fun Date.getOffsetDate(): String {
    val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    return format.format(this)
}

private fun String.isDateInISO8601(): Boolean {
    return try {
        val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        format.parse(this)
        true
    } catch (e: ParseException) {
        false
    }
}

fun getCurrentDate(): String {
    val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.US)
    format.timeZone = TimeZone.getTimeZone("UTC")
    val date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis
    return format.format(Date(date))
}
