package com.example.beesafeexample.core.extension

import androidx.fragment.app.FragmentTransaction
import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseFragment

fun <F: BaseFragment> FragmentTransaction.simpleReplace(frameLayoutID: Int, fragment: F) {
    setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_popup_exit)
    replace(frameLayoutID, fragment, fragment::class.simpleName)
    addToBackStack(fragment::class.simpleName)
    commit()
}