package com.atef.data.remote.feature.usermanagement.service

import com.atef.data.remote.feature.usermanagement.model.request.*
import com.atef.data.remote.feature.usermanagement.model.response.GetUserResponse
import com.atef.data.remote.feature.usermanagement.model.response.LoginResponse
import com.atef.data.remote.feature.usermanagement.model.response.SignUpResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UserManagementService {

    @POST("login")
    fun login(@Body request: LoginCredentialRequest): Single<LoginResponse>

    @POST("signup")
    fun signUp(@Body request: SignUpRequest): Single<SignUpResponse>

    @POST("confirm")
    fun verifyByEmail(@Body request: VerifyEmailRequest): Completable

    @POST("forget")
    fun forgetPassword(@Body request: ForgetPasswordRequest): Completable

    @POST("forgot/password")
    fun resetPassword(@Body request: ResetPasswordRequest): Completable

    @POST("logout")
    fun logout(): Completable

    @GET("users/{userId}")
    fun fetchUser(@Path("userId") userId: String): Single<GetUserResponse>

    @PATCH("users/update")
    fun updateUser(@Body request: UpdateUserRequest): Single<GetUserResponse>
}
