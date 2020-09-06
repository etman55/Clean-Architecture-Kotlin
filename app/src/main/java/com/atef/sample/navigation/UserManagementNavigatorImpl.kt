package com.atef.sample.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.atef.sample.R
import com.atef.sample.feature.MainActivity
import com.atef.sample.feature.cloudmessaging.CaseStatus
import com.atef.sample.feature.usermanagement.UserManagementActivity
import javax.inject.Inject

class UserManagementNavigatorImpl @Inject constructor() : UserManagementNavigator {
    private val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(R.id.splashFragment, true)
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()

    private val menuNavOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_bottom)
        .setExitAnim(R.anim.slide_out_top)
        .setPopEnterAnim(R.anim.slide_in_top)
        .setPopExitAnim(R.anim.slide_out_bottom)
        .build()

    private val navOptionsNoAnimations = NavOptions.Builder()
        .setPopUpTo(R.id.splashFragment, true)
        .build()

    override fun openSplash(fragment: Fragment) {
        val intent = Intent(fragment.requireContext(), UserManagementActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        fragment.startActivity(
            intent/*,
        ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()*/
        )
        fragment.requireActivity().finish()
    }

    override fun openSplash(activity: Activity) {
        if (activity is UserManagementActivity) return
        val intent = Intent(activity, UserManagementActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(
            intent/*,
        ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()*/
        )
        activity.finish()
    }

    override fun openLogin(activity: Activity) {
        if (activity is UserManagementActivity) return
        val intent = Intent(activity, UserManagementActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(
            intent/*,
        ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()*/
        )
        activity.finish()
    }

    override fun openLogin(navController: NavController?) {
        val uri = "app://navigation/login"
        val deepLink = Uri.parse(uri)
        navController?.navigate(deepLink, navOptionsNoAnimations)
    }

    override fun updatePerStatus(activity: Activity, status: CaseStatus?) {
        when (status) {
            CaseStatus.Status1 -> {
            }
            CaseStatus.Status2 ->
                if (activity !is UserManagementActivity) openLogin(activity)
            null -> {
                // Kindly, no action here
            }
        }
    }

    override fun goBack(navController: NavController) {
        navController.popBackStack()
    }

    override fun openMainActivity(activity: Activity) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(
            intent/*,
        ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()*/
        )
        activity.finish()
    }

    override fun openRegistration(navController: NavController) {
        navController.navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    override fun openVerifyEmail(navController: NavController, email: String) {
        navController.navigate(R.id.action_signUpFragment_to_verifyEmailFragment)
    }
}
