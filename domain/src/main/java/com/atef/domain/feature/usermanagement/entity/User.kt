package com.atef.domain.feature.usermanagement.entity

data class User(
    val id: String?,
    val email: String?,
    val name: String? = null,
    val phone: String? = null,
    val image: String? = null
)
