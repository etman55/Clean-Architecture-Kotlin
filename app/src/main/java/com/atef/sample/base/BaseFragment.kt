package com.atef.sample.base

import android.content.Context
import android.content.res.Configuration
import androidx.fragment.app.Fragment
import com.atef.core.dialog.NetworkStateDialog
import com.atef.core.locale.LocalizationHandler
import com.atef.sample.navigation.UserManagementNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {
    var baseActivity: BaseActivity? = null
        private set

    @Inject
    lateinit var userManagementNavigator: UserManagementNavigator

    override fun onAttach(context: Context) {
        super.onAttach(LocalizationHandler.onAttach(context))
        if (context is BaseActivity) {
            val activity = context
            baseActivity = activity
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        requireActivity().let { LocalizationHandler.onAttach(it) }
    }

    protected fun showLoading() {
        NetworkStateDialog.show(requireContext())
    }

    protected fun hideLoading() {
        NetworkStateDialog.dismiss()
    }
}
