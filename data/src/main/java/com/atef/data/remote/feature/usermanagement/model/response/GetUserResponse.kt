package com.atef.data.remote.feature.usermanagement.model.response

import androidx.annotation.Keep
import com.atef.data.remote.base.model.BaseResponse
import com.atef.data.remote.feature.usermanagement.model.UserModel

@Keep
data class GetUserResponse(val `data`: UserModel) : BaseResponse()
