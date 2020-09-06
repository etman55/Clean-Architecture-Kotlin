package com.atef.sample.di.module

import com.atef.sample.navigation.UserManagementNavigator
import com.atef.sample.navigation.UserManagementNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
@InstallIn(ApplicationComponent::class)
@Module
interface NavigationModule {

    @get:Binds
    val UserManagementNavigatorImpl.userManagementNavigator: UserManagementNavigator
}
