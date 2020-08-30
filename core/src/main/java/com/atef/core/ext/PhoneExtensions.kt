package com.atef.core.ext

import android.telephony.PhoneNumberUtils
import java.util.Locale

/**
 * Formats the specified {@code phoneNumber} to the E.164 representation.
 *
 * @param phoneNumber the phone number to format.
 * @param defaultCountryIso the ISO 3166-1 two letters country code.
 * @return the E.164 representation, or null if the given phone number is not valid.
 */
fun formatNumberToE164(phoneNumber: String, defaultCountryIso: String): String? {
    return PhoneNumberUtils.formatNumberToE164(phoneNumber, defaultCountryIso)
}

fun isValidPhone(phoneNumber: String, defaultCountryIso: String): Boolean {
    return formatNumberToE164(
        phoneNumber,
        defaultCountryIso
    ) != null
}

fun getCountryIso(countryName: String): String {
    val countryIso = Locale.getISOCountries()
        .find {
            Locale("", it)
                .getDisplayCountry(Locale.ENGLISH).equals(countryName, true)
        }
    if (countryIso != null) return countryIso
    else throw IllegalArgumentException("Country Name isn't valid")
}
