package com.atef.data.remote.feature.usermanagement.model.response

import androidx.annotation.Keep
import com.atef.data.remote.base.model.BaseResponse
import com.squareup.moshi.Json

@Keep
data class SignUpResponse(
    val `data`: Data? = null
) : BaseResponse() {

    @Keep
    data class Data(
        @Json(name = "access_token")
        val accessToken: String? = null,
        val id: String? = null,
        val email: String? = null,
        val name: String? = null,
        val phone: String? = null,
        val image: String? = null
    )
}
