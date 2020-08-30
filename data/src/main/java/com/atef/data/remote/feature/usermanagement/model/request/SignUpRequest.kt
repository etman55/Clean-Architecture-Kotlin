package com.atef.data.remote.feature.usermanagement.model.request

import androidx.annotation.Keep

@Keep
data class SignUpRequest(
    val email: String? = null,
    val password: String? = null
)
