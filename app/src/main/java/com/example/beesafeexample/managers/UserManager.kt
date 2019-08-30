package com.example.beesafeexample.managers

import com.pixplicity.easyprefs.library.Prefs

object UserManager {
    var isLogin: Boolean
        get() = Prefs.getBoolean("isLogin", false)
        set(value) = Prefs.putBoolean("isLogin", value)

    var userName: String?
        get() = Prefs.getString("userName", null)
        set(value) = Prefs.putString("loggedIn", value)
}