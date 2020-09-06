package com.atef.sample.feature.usermanagement

import android.os.Bundle
import androidx.navigation.findNavController
import com.atef.sample.R
import com.atef.sample.base.BaseActivity

class UserManagementActivity : BaseActivity(R.layout.activity_user_management) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (!onSupportNavigateUp()) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.userManagemenFragment)
        return navController.navigateUp()
    }
}
