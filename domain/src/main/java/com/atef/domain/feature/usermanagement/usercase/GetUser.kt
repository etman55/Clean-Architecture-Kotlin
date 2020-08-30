package com.atef.domain.feature.usermanagement.usercase

import com.atef.domain.base.errorhandling.ErrorHandler
import com.atef.domain.base.scheduler.BaseSchedulerProvider
import com.atef.domain.base.usecase.ObservableUseCase
import com.atef.domain.feature.usermanagement.entity.User
import com.atef.domain.feature.usermanagement.repository.UserManagementRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUser @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    baseSchedulerProvider: BaseSchedulerProvider,
    errorHandler: ErrorHandler
) : ObservableUseCase<Nothing?, User>(baseSchedulerProvider, errorHandler) {

    public override fun createUseCase(params: Nothing?): Observable<User> {
        return userManagementRepository.getUser()
    }
}
