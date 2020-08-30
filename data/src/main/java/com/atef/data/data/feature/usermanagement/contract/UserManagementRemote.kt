package com.atef.data.data.feature.usermanagement.contract

import com.atef.data.data.feature.usermanagement.model.UserDataModel
import com.atef.data.remote.feature.usermanagement.model.request.UpdateUserRequest
import com.atef.data.remote.feature.usermanagement.model.response.LoginResponse
import com.atef.data.remote.feature.usermanagement.model.response.SignUpResponse
import io.reactivex.Completable
import io.reactivex.Observable

interface UserManagementRemote {

    fun login(email: String, password: String): Observable<LoginResponse>

    fun signUp(email: String, password: String): Observable<SignUpResponse>

    fun verifyByEmail(email: String, pinCode: Int): Completable

    fun forgetPassword(email: String): Completable

    fun resetPassword(pinCode: String, password: String): Completable

    fun logout(): Completable

    fun fetchUser(userId: String): Observable<UserDataModel>

    fun updateUser(updateUserRequest: UpdateUserRequest): Observable<UserDataModel>
}
