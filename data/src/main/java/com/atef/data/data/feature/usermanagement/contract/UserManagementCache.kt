package com.atef.data.data.feature.usermanagement.contract

import com.atef.data.data.feature.usermanagement.model.UserDataModel
import io.reactivex.Completable
import io.reactivex.Single

interface UserManagementCache {

    fun getUser(): UserDataModel?

    fun clearUser()

    fun saveUser(userDataModel: UserDataModel, lastCache: Long)

    fun areUserCached(): Boolean

    fun persistToken(key: String, value: String)

    fun clearToken(key: String)

    fun getToken(key: String): Single<String>

    fun logout(): Completable

}
