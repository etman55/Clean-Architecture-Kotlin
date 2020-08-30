package com.atef.core.di.module

import android.content.Context
import android.content.SharedPreferences
import com.atef.core.preference.PreferencesGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
@InstallIn(ApplicationComponent::class)
@Module
object PreferenceModule {

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
    }

    @[Provides Singleton]
    fun providePreferenceGateWay(prefs: SharedPreferences): PreferencesGateway =
        PreferencesGateway(prefs)
}
