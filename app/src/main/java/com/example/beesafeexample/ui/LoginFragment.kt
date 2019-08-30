package com.example.beesafeexample.ui

import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseInputFragment
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.redux.states.AuthenticationState
import org.rekotlin.StoreSubscriber

class LoginFragment(override var params: Params?): BaseInputFragment<LoginFragment.Params>(params), StoreSubscriber<AuthenticationState> {

    data class Params(
        val onNext: () -> Unit
    ): BaseParams()

    override val layoutResource: Int
        get() = R.layout.fragment_login


    override fun setupUI() {
        super.setupUI()

    }


    override fun newState(state: AuthenticationState) {
        showOrHideLoading(state.isFetching)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainStore.unsubscribe(this)
    }
}