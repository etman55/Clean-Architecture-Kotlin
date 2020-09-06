package com.atef.data.cache.feature.usermangement

import com.atef.core.preference.PreferencesGateway
import com.atef.core.preference.PreferencesGateway.Companion.DEFAULT_VALUE
import com.atef.core.preference.fromObjectToString
import com.atef.core.preference.fromStringToObject
import com.atef.data.data.feature.usermanagement.contract.UserManagementCache
import com.atef.data.data.feature.usermanagement.model.UserDataModel
import com.atef.data.remote.base.interceptor.RequestHeaders
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import timber.log.Timber

class UserManagementCacheDataStore @Inject constructor(
    private val preferencesGateway: PreferencesGateway
) : UserManagementCache {

    companion object {
        const val KEY_USER = "user"
    }

    override fun clearUser() {
        preferencesGateway.remove(KEY_USER)
        clearToken(RequestHeaders.AUTHORIZATION_SHORT_TERM_TOKEN)
    }

    override fun saveUser(userDataModel: UserDataModel, lastCache: Long) {
        preferencesGateway.save(
            KEY_USER,
            userDataModel.fromObjectToString(UserDataModel::class.java)
        )
    }

    override fun areUserCached(): Boolean {
        val user: UserDataModel? = preferencesGateway
            .load(KEY_USER, DEFAULT_VALUE)
            .blockingGet()
            .fromStringToObject(UserDataModel::class.java) as UserDataModel
        Timber.d("areUserCached(): $user")
        return user != null
    }

    override fun persistToken(key: String, value: String) {
        preferencesGateway.save(key, value)
    }

    override fun clearToken(key: String) {
        preferencesGateway.remove(key)
    }

    override fun getToken(key: String): Single<String> {
        return preferencesGateway.load(key, DEFAULT_VALUE)
    }

    override fun logout(): Completable {
        clearUser()
        return Completable.complete()
    }

    override fun getUser(): UserDataModel? {
        val user = preferencesGateway
            .load(KEY_USER, DEFAULT_VALUE)
            .blockingGet()
            .fromStringToObject(UserDataModel::class.java) as UserDataModel?
        Timber.d("getUser(): $user")
        return user
    }
}
