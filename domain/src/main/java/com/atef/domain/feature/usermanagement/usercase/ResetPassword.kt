package com.atef.domain.feature.usermanagement.usercase

import com.atef.domain.base.exception.InvalidCredentialException
import com.atef.domain.base.exception.requireParams
import com.atef.domain.base.ext.isPasswordValid
import com.atef.domain.base.scheduler.BaseSchedulerProvider
import com.atef.domain.base.usecase.CompletableUseCase
import com.atef.domain.feature.usermanagement.repository.UserManagementRepository
import io.reactivex.Completable
import javax.inject.Inject

class ResetPassword @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    baseSchedulerProvider: BaseSchedulerProvider
) : CompletableUseCase<ResetPassword.Params?>(baseSchedulerProvider) {

    public override fun createUseCase(params: Params?): Completable {
        requireParams(params)
        return if (params.password.isPasswordValid())
            userManagementRepository.resetPassword(params.pinCode, params.password)
        else
            Completable.error(InvalidCredentialException())
    }

    data class Params(val pinCode: String, val password: String)
}
