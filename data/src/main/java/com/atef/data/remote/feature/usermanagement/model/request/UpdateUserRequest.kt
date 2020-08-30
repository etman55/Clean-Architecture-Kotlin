package com.atef.data.remote.feature.usermanagement.model.request

import androidx.annotation.Keep

@Keep
data class UpdateUserRequest(
    val name: String? = null,
    val phone: String? = null,
    val image: String? = null
)
