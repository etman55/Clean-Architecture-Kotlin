package com.atef.domain.feature.usermanagement.usercase

import com.atef.domain.base.exception.InvalidCredentialException
import com.atef.domain.base.exception.requireParams
import com.atef.domain.base.ext.isEmailValid
import com.atef.domain.base.scheduler.BaseSchedulerProvider
import com.atef.domain.base.usecase.CompletableUseCase
import com.atef.domain.feature.usermanagement.repository.UserManagementRepository
import io.reactivex.Completable
import javax.inject.Inject

class ForgetPassword @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    baseSchedulerProvider: BaseSchedulerProvider
) : CompletableUseCase<ForgetPassword.Params?>(baseSchedulerProvider) {

    override fun createUseCase(params: Params?): Completable {
        requireParams(params)
        return if (params.email.isEmailValid())
            userManagementRepository.forgetPassword(params.email)
        else
            Completable.error(InvalidCredentialException())
    }

    data class Params(val email: String)
}
