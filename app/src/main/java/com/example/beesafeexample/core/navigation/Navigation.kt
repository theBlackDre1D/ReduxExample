package com.example.beesafeexample.core.navigation

import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseActivity
import com.example.beesafeexample.core.base.BaseFragment
import com.example.beesafeexample.core.extension.simpleReplace
import kotlin.reflect.KClass

object Navigation {

    // FRAGMENT

    fun <A: BaseActivity, F: BaseFragment> switchFragments(activity: A, newFragment: F, clearBackStack: Boolean = false) {
        if (clearBackStack) clearBackStack(activity)
        val transition = activity.supportFragmentManager.beginTransaction()

        transition.simpleReplace(R.id.container, newFragment)
    }

    fun pop(activity: BaseActivity) {
        activity.supportFragmentManager.popBackStack()
    }

    fun <T: BaseFragment> popToFragment(fragClass: KClass<T>, activity: BaseActivity) {
        activity.supportFragmentManager.popBackStackImmediate(fragClass.simpleName, 0)
    }

    private fun clearBackStack(activity: BaseActivity) {
        val fm = activity.supportFragmentManager
        while (fm.backStackEntryCount > 0) {
            fm.popBackStackImmediate()
        }
    }


    // ACTIVITY
}