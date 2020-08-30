package com.atef.data.data.di.module

import com.atef.data.data.feature.usermanagement.contract.UserManagementRemote
import com.atef.data.remote.feature.usermanagement.UserManagementRemoteDataStore
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
interface RemoteModule {

    @get:[Binds Singleton]
    val UserManagementRemoteDataStore.userManagementRemoteDataStore: UserManagementRemote
}
