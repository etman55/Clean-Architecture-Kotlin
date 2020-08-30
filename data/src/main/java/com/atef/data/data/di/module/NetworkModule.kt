package com.atef.data.data.di.module

import android.app.Application
import com.atef.data.BuildConfig
import com.atef.data.data.feature.usermanagement.contract.UserManagementCache
import com.atef.data.remote.base.factory.RetrofitFactory
import com.atef.data.remote.feature.usermanagement.service.UserManagementService
import dagger.Module
import dagger.Provides
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
object NetworkModule {

    @[Provides Singleton]
    fun provideUserManagementService(
        baseUrl: String,
        userManagementCache: UserManagementCache,
        application: Application
    ): UserManagementService =
        RetrofitFactory.makeServiceHandler(
            baseUrl,
            UserManagementService::class.java,
            BuildConfig.DEBUG,
            userManagementCache,
            application
        ) as UserManagementService

    @[Provides Singleton]
    fun provideBaseURL(): String = "http://baseurl.example"
}
