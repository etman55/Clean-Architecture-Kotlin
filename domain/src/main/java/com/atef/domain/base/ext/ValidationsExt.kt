package com.atef.domain.base.ext

import java.util.regex.Pattern

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */

/**
 * Returns `true` if email is in valid email format
 */
fun String.isEmailValid(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isUrlValid(): Boolean {
    val expression =
        "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/|www\\.)[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isCharsOnly(): Boolean {
    val expression = "^[\\u0600-\\u065F\\u066A-\\u06EF\\u06FA-\\u06FF-a-zA-Z]*\$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isCharsAndSymbols(): Boolean {
    val expression =
        "^[\\u0600-\\u065F\\u066A-\\u06EF\\u06FA-\\u06FF-a-zA-Z`~!@#\$%^&*()_+[];',./{}|:\"<>?]]*\$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isCharsAndNumbers(): Boolean {
    val oneLowercaseCharacter = ".*[a-z].*"
    val oneUppercaseCharacter = ".*[A-Z].*"
    val oneNumber = ".*\\d.*"
    return (Pattern.compile(oneLowercaseCharacter).matcher(this).find() &&
        Pattern.compile(oneNumber).matcher(this).find() &&
        Pattern.compile(oneUppercaseCharacter).matcher(this).find())
}

fun String.isSymbolsOnly(): Boolean {
    val expression = "^[^A-Za-z0-9]*\$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isNumbersOnly(): Boolean {
    val expression = "[0-9]+"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isCharsOnlyAndArabic(): Boolean {
    val expression = "^[\u0621-\u064A\u0660-\u0669]*\$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isPasswordValid(): Boolean {
    return this.length >= 6
}
