package com.atef.sample.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.atef.core.dialog.NetworkStateDialog
import com.atef.core.locale.LocalizationHandler
import com.atef.sample.feature.cloudmessaging.StatusManager
import com.atef.sample.navigation.UserManagementNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity(@LayoutRes private val layoutRes: Int = 0) :
    AppCompatActivity(layoutRes) {

    @Inject
    lateinit var userManagementNavigator: UserManagementNavigator

    @Inject
    lateinit var statusManager: StatusManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocalizationHandler.onAttach(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocalizationHandler.onAttach(this)
    }

    protected fun showLoading() {
        NetworkStateDialog.show(this)
    }

    protected fun hideLoading() {
        NetworkStateDialog.dismiss()
    }

    private fun subscribeObservers() {
        statusManager.status.removeObservers(this)
        statusManager.status.observe(this, Observer {
            userManagementNavigator.updatePerStatus(this, it.data)
        })
    }
}
