package com.example.beesafeexample.redux.routes

import android.content.Context
import android.content.Intent
import com.example.beesafeexample.AppController
import com.example.beesafeexample.core.base.BaseFragment
import com.example.beesafeexample.core.navigation.Navigation
import com.example.beesafeexample.ui.activities.LoginActivity

object RoutableHelper {

    fun mainActivityRoutable(context: Context): MainActivityRouteble {
        return MainActivityRouteble(context)
    }

    fun backStackFragmentRoutable(fragment: BaseFragment): FragmentRoutable {
        val activity = AppController.currentActivity.get()!!
        Navigation.switchFragments(activity, fragment)

        return FragmentRoutable(activity.applicationContext)
    }

    fun loginActivityRoutable(context: Context): LoginActivityRoutable {
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)

        return LoginActivityRoutable(context)
    }
}