package com.atef.domain.feature.usermanagement.usercase

import com.atef.domain.base.scheduler.BaseSchedulerProvider
import com.atef.domain.base.usecase.CompletableUseCase
import com.atef.domain.feature.usermanagement.repository.UserManagementRepository
import io.reactivex.Completable
import javax.inject.Inject

class Logout @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    baseSchedulerProvider: BaseSchedulerProvider
) : CompletableUseCase<Nothing?>(baseSchedulerProvider) {

    override fun createUseCase(params: Nothing?): Completable {
        return userManagementRepository.logout()
    }
}
