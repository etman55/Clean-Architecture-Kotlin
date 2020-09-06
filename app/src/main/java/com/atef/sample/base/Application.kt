package com.atef.sample.base

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.atef.core.locale.LocalizationHandler
import com.atef.sample.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
@HiltAndroidApp
class Application : Application(), LifecycleObserver {

    private var appUpdateManager: AppUpdateManager? = null

    override fun onCreate() {
        super.onCreate()
        initAppUpdateManager()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        if (BuildConfig.DEBUG)
            setupTimber()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { LocalizationHandler.onAttach(it) })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocalizationHandler.onAttach(this)
    }

    private fun initAppUpdateManager() {
        if (appUpdateManager == null) appUpdateManager =
            AppUpdateManager(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
