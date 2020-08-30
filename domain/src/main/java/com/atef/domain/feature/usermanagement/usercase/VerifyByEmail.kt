package com.atef.domain.feature.usermanagement.usercase

import com.atef.domain.base.exception.InvalidCodeException
import com.atef.domain.base.exception.requireParams
import com.atef.domain.base.ext.isEmailValid
import com.atef.domain.base.scheduler.BaseSchedulerProvider
import com.atef.domain.base.usecase.CompletableUseCase
import com.atef.domain.feature.usermanagement.repository.UserManagementRepository
import io.reactivex.Completable
import javax.inject.Inject

class VerifyByEmail @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    baseSchedulerProvider: BaseSchedulerProvider
) : CompletableUseCase<VerifyByEmail.Params?>(baseSchedulerProvider) {

    public override fun createUseCase(params: Params?): Completable {
        requireParams(params)
        return if (params.email.isEmailValid() && params.pinCode.length == 5)
            userManagementRepository.verifyByEmail(params.email, params.pinCode.toInt())
        else
            Completable.error(InvalidCodeException())
    }

    data class Params(val email: String, val pinCode: String)
}
