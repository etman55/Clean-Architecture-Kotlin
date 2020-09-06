package com.atef.sample.navigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.atef.sample.feature.cloudmessaging.CaseStatus

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
interface UserManagementNavigator {
    fun openSplash(activity: Activity)
    fun openSplash(fragment: Fragment)
    fun openLogin(activity: Activity)
    fun openLogin(navController: NavController?)
    fun updatePerStatus(activity: Activity, status: CaseStatus?)
    fun goBack(navController: NavController)
    fun openMainActivity(activity: Activity)
    fun openRegistration(navController: NavController)
    fun openVerifyEmail(navController: NavController, email: String)
}
