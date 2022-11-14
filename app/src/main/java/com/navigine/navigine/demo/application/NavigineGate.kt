package com.navigine.navigine.demo.application

import android.content.Context
import android.content.Intent
import com.navigine.navigine.demo.ui.activities.LoginActivity

object NavigineGate {
    @JvmStatic
    fun getNavigineIntent(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }
}