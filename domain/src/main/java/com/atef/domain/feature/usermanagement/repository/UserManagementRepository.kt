package com.atef.domain.feature.usermanagement.repository

import com.atef.domain.feature.usermanagement.entity.User
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */

interface UserManagementRepository {

    fun login(email: String, password: String): Observable<User>

    // we can use otp instead of password
    fun verifyByEmail(email: String, pinCode: Int): Completable

    fun forgetPassword(email: String): Completable

    fun resetPassword(pinCode: String, password: String): Completable

    fun signUp(
        email: String,
        password: String
    ): Observable<User>

    fun logout(): Completable

    // get user from cache
    fun getUser(): Observable<User>
}
