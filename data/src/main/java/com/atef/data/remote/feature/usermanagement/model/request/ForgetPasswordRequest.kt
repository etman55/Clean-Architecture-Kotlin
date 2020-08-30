package com.atef.data.remote.feature.usermanagement.model.request

import androidx.annotation.Keep

@Keep
data class ForgetPasswordRequest(
    val email: String? = null
)
