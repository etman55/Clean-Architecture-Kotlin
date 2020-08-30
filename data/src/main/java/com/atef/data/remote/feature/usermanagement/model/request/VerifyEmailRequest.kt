package com.atef.data.remote.feature.usermanagement.model.request

import androidx.annotation.Keep

@Keep
data class VerifyEmailRequest(
    val code: Int? = null,
    val email: String? = null
)
