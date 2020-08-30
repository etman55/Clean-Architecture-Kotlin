package com.atef.data.remote.feature.usermanagement.model.response

import androidx.annotation.Keep
import com.atef.data.remote.base.model.BaseResponse
import com.atef.data.remote.feature.usermanagement.model.UserModel
import com.squareup.moshi.Json

@Keep
data class LoginResponse(
    val `data`: Data? = null
) : BaseResponse() {

    @Keep
    data class Data(
        @Json(name = "access_token")
        val accessToken: String? = null,
        val user: UserModel? = null
    )
}
