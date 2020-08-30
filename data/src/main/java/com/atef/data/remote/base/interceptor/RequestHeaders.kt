package com.atef.data.remote.base.interceptor

import android.app.Application
import com.atef.core.locale.LocalizationHandler
import com.atef.data.data.feature.usermanagement.contract.UserManagementCache
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
class RequestHeaders @Inject constructor(
    private val userManagementCache: UserManagementCache,
    @ApplicationContext private val application: Application
) {

    companion object {
        const val AUTHORIZATION_SHORT_TERM_TOKEN = "Authorization"
        const val ACCEPT_LANGUAGE = "Accept-Language"
    }

    fun getShortTermTokenName() = AUTHORIZATION_SHORT_TERM_TOKEN

    fun getShortTermTokenValue(): String =
        userManagementCache.getToken(getShortTermTokenName())
            .blockingGet()

    fun hasShortTermToken() = getShortTermTokenValue().isNotBlank()

    fun setShortTermTokenValue(value: String) {
        userManagementCache.persistToken(getShortTermTokenName(), value)
    }

    fun getAcceptLanguageKey() = ACCEPT_LANGUAGE

    fun getLanguageValue() = LocalizationHandler.getCurrentLocale(application)
}
