package com.atef.data.remote.feature.usermanagement.mapper

import com.atef.data.data.feature.usermanagement.model.UserDataModel
import com.atef.data.remote.base.mapper.RemoteMapper
import com.atef.data.remote.feature.usermanagement.model.UserModel
import javax.inject.Inject

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */

class UserRemoteMapper @Inject constructor() : RemoteMapper<UserModel, UserDataModel> {
    override fun mapFromModel(model: UserModel): UserDataModel {
        return with(model) {
            UserDataModel(
                id,
                email,
                name,
                phone,
                image
            )
        }
    }

    override fun mapToModel(entity: UserDataModel): UserModel {
        return with(entity) {
            UserModel(
                id,
                email,
                name,
                phone,
                image
            )
        }
    }
}
