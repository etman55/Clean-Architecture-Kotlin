package com.atef.data.remote.feature.usermanagement.model

import androidx.annotation.Keep

@Keep
data class UserModel(
    val id: String? = null,
    val email: String? = null,
    val name: String? = null,
    val phone: String? = null,
    val image: String? = null
)