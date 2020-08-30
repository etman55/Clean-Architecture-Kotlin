package com.atef.data.remote.feature.usermanagement

import com.atef.data.data.feature.usermanagement.contract.UserManagementRemote
import com.atef.data.data.feature.usermanagement.model.UserDataModel
import com.atef.data.remote.feature.usermanagement.mapper.UserRemoteMapper
import com.atef.data.remote.feature.usermanagement.model.request.*
import com.atef.data.remote.feature.usermanagement.model.response.LoginResponse
import com.atef.data.remote.feature.usermanagement.model.response.SignUpResponse
import com.atef.data.remote.feature.usermanagement.service.UserManagementService
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class UserManagementRemoteDataStore @Inject constructor(
    private val service: UserManagementService,
    private val mapper: UserRemoteMapper
) : UserManagementRemote {

    override fun login(email: String, password: String): Observable<LoginResponse> {
        return service.login(LoginCredentialRequest(email, password))
            .toObservable()
    }

    override fun signUp(email: String, password: String): Observable<SignUpResponse> {
        return service.signUp(SignUpRequest(email, password)).toObservable()
    }

    override fun verifyByEmail(email: String, pinCode: Int): Completable {
        return service.verifyByEmail(VerifyEmailRequest(pinCode, email))
    }

    override fun forgetPassword(email: String): Completable {
        return service.forgetPassword(ForgetPasswordRequest(email))
    }

    override fun resetPassword(pinCode: String, password: String): Completable {
        return service.resetPassword(ResetPasswordRequest(pinCode, password))
    }

    override fun logout(): Completable {
        return service.logout()
    }

    override fun fetchUser(userId: String): Observable<UserDataModel> {
        return service.fetchUser(userId)
            .toObservable()
            .map { mapper.mapFromModel(it.data) }
    }

    override fun updateUser(updateUserRequest: UpdateUserRequest): Observable<UserDataModel> {
        return service.updateUser(updateUserRequest)
            .toObservable()
            .map { mapper.mapFromModel(it.data) }
    }
}
