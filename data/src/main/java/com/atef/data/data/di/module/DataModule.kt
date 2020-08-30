package com.atef.data.data.di.module

import com.atef.data.data.base.errorhandling.ErrorHandlerImpl
import com.atef.data.data.feature.usermanagement.repository.UserManagementDataRepository
import com.atef.domain.base.errorhandling.ErrorHandler
import com.atef.domain.feature.usermanagement.repository.UserManagementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
@InstallIn(ApplicationComponent::class)
@Module
interface DataModule {

    @get:[Binds Singleton]
    val UserManagementDataRepository.userManagementDataRepository: UserManagementRepository

    @get:[Binds Singleton]
    val ErrorHandlerImpl.errorHandler: ErrorHandler
}
