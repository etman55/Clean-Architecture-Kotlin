package com.atef.data.data.feature.usermanagement.mapper

import com.atef.data.data.base.mapper.DataMapper
import com.atef.data.data.feature.usermanagement.model.UserDataModel
import com.atef.domain.feature.usermanagement.entity.User
import javax.inject.Inject

class UserMapper @Inject constructor() : DataMapper<UserDataModel, User> {
    override fun mapFromData(data: UserDataModel): User {
        return with(data) {
            User(
                id,
                email,
                name,
                phone,
                image
            )
        }
    }

    override fun mapToData(domain: User): UserDataModel {
        return with(domain) {
            UserDataModel(
                id,
                email,
                name,
                phone,
                image
            )
        }
    }
}
