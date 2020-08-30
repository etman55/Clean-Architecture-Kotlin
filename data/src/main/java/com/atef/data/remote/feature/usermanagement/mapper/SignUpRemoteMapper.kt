package com.atef.data.remote.feature.usermanagement.mapper

import com.atef.data.data.feature.usermanagement.model.UserDataModel
import com.atef.data.remote.base.mapper.RemoteMapper
import com.atef.data.remote.feature.usermanagement.model.response.SignUpResponse
import javax.inject.Inject

class SignUpRemoteMapper @Inject constructor(
) : RemoteMapper<SignUpResponse, UserDataModel> {
    override fun mapFromModel(model: SignUpResponse): UserDataModel {
        return with(model.data!!) {
            UserDataModel(
                id,
                email,
                name,
                phone,
                image
            )
        }
    }

    override fun mapToModel(entity: UserDataModel): SignUpResponse {
        return with(entity) {
            SignUpResponse(
                SignUpResponse.Data(
                    "",
                    id,
                    email,
                    name,
                    phone,
                    image
                )
            )
        }
    }
}
