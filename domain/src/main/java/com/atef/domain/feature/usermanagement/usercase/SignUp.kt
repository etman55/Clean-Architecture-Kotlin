package com.atef.domain.feature.usermanagement.usercase

import com.atef.domain.base.errorhandling.ErrorHandler
import com.atef.domain.base.exception.InvalidCredentialException
import com.atef.domain.base.exception.requireParams
import com.atef.domain.base.ext.isEmailValid
import com.atef.domain.base.ext.isPasswordValid
import com.atef.domain.base.scheduler.BaseSchedulerProvider
import com.atef.domain.base.usecase.ObservableUseCase
import com.atef.domain.feature.usermanagement.entity.User
import com.atef.domain.feature.usermanagement.repository.UserManagementRepository
import io.reactivex.Observable
import javax.inject.Inject

class SignUp @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    baseSchedulerProvider: BaseSchedulerProvider,
    errorHandler: ErrorHandler
) : ObservableUseCase<SignUp.Params?, User>(baseSchedulerProvider, errorHandler) {

    public override fun createUseCase(params: Params?): Observable<User> {
        requireParams(params)
        return if (!params.email.isEmailValid() && !params.password.isPasswordValid() ||
            params.confirmPassword != params.password
        )
            Observable.error(InvalidCredentialException())
        else
            userManagementRepository.signUp(
                params.email,
                params.password
            )
    }

    data class Params(
        val type: String,
        val email: String,
        val password: String,
        val confirmPassword: String
    )
}
