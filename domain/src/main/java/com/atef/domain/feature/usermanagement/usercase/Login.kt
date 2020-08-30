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

class Login @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    baseSchedulerProvider: BaseSchedulerProvider,
    errorHandler: ErrorHandler
) : ObservableUseCase<Login.Params?, User>(baseSchedulerProvider, errorHandler) {

    public override fun createUseCase(params: Params?): Observable<User> {
        requireParams(params)
        return if (params.email.isNotEmpty() || params.email.isEmailValid() || params.password.isPasswordValid())
            userManagementRepository.login(params.email, params.password)
        else
            Observable.error(InvalidCredentialException())
    }

    data class Params(val email: String, val password: String)
}
