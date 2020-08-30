package com.atef.data.remote.feature.usermanagement.model.request

import androidx.annotation.Keep

@Keep
data class LoginCredentialRequest(
    var email: String? = null,
    var password: String? = null
)
