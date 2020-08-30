package com.atef.data.data.feature.usermanagement.repository

import com.atef.data.data.feature.usermanagement.contract.UserManagementCache
import com.atef.data.data.feature.usermanagement.mapper.UserMapper
import com.atef.data.remote.base.interceptor.RequestHeaders
import com.atef.data.remote.feature.usermanagement.UserManagementRemoteDataStore
import com.atef.data.remote.feature.usermanagement.mapper.SignUpRemoteMapper
import com.atef.data.remote.feature.usermanagement.mapper.UserRemoteMapper
import com.atef.domain.base.exception.NoUserFoundException
import com.atef.domain.feature.usermanagement.entity.User
import com.atef.domain.feature.usermanagement.repository.UserManagementRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject
import timber.log.Timber

class UserManagementDataRepository @Inject constructor(
    private val mapper: UserMapper,
    private val userRemoteMapper: UserRemoteMapper,
    private val signUpRemoteMapper: SignUpRemoteMapper,
    private val cache: UserManagementCache,
    private val remote: UserManagementRemoteDataStore
) : UserManagementRepository {

    override fun login(email: String, password: String): Observable<User> {
        return remote.login(email, password)
            .map { response ->
                val userModel = userRemoteMapper.mapFromModel(response.data?.user!!)
                cache.saveUser(userModel, System.currentTimeMillis())
                cache.persistToken(
                    RequestHeaders.AUTHORIZATION_SHORT_TERM_TOKEN,
                    response.data.accessToken!!
                )
                mapper.mapFromData(userModel)
            }
    }

    override fun verifyByEmail(email: String, pinCode: Int): Completable {
        return remote.verifyByEmail(email, pinCode)
    }

    override fun forgetPassword(email: String): Completable {
        return remote.forgetPassword(email)
    }

    override fun resetPassword(pinCode: String, password: String): Completable {
        return remote.resetPassword(pinCode, password)
    }

    override fun signUp(
        email: String,
        password: String
    ): Observable<User> {
        return remote.signUp(email, password)
            .map { response ->
                val userModel = signUpRemoteMapper.mapFromModel(response)
                cache.persistToken(
                    RequestHeaders.AUTHORIZATION_SHORT_TERM_TOKEN,
                    response.data?.accessToken!!
                )
                cache.saveUser(userModel, System.currentTimeMillis())
                mapper.mapFromData(userModel)
            }
    }

    override fun logout(): Completable {
        return remote.logout().doOnComplete { cache.logout() }
    }

    override fun getUser(): Observable<User> {
        return Observable.create { emitter ->
            val isCached = cache.areUserCached()
            Timber.d("userObservable $isCached")
            if (isCached) {
                val model = cache.getUser()
                model?.let {
                    val user = mapper.mapFromData(it)
                    emitter.onNext(user)
                } ?: run {
                    emitter.onError(NoUserFoundException())
                }
            } else
                emitter.onError(NoUserFoundException())
        }
    }
}
