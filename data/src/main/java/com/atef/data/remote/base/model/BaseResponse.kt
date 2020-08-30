package com.atef.data.remote.base.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
open class BaseResponse {
    @Json(name = "status_code")
    val statusCode: Int? = null
    var errors: List<String?>? = null
    val message: String? = null
}
