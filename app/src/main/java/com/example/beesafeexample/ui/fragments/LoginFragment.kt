package com.example.beesafeexample.ui.fragments

import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseInputFragment
import com.example.beesafeexample.core.extension.getFullText
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.redux.actions.LoginActions
import com.example.beesafeexample.redux.states.AuthenticationState
import kotlinx.android.synthetic.main.fragment_login.*
import org.rekotlin.StoreSubscriber

class LoginFragment(override var params: Params?
): BaseInputFragment<LoginFragment.Params>(params), StoreSubscriber<AuthenticationState> {

    data class Params(
        val onNext: () -> Unit
    ): BaseParams()

    override val layoutResource: Int
        get() = R.layout.fragment_login



    override fun setupUI() {
        super.setupUI()

        setupButtons()
        setupSubscription()
    }

    private fun setupSubscription() {
        mainStore.subscribe(this) {
            it.select { it.authenticationState }.skipRepeats { oldState, newState ->  oldState == newState }
        }
    }

    private fun setupButtons() {
        bGetCode.setOnClickListener {
            mainStore.dispatch(LoginActions.RequestSMSCode(etPhoneNumber.getFullText()))
        }

        bLogin.setOnClickListener {
            mainStore.dispatch(LoginActions.VerifySMSCode(etCode.getFullText()))
        }
    }



    override fun newState(state: AuthenticationState) {
        showOrHideLoading(state.isFetching)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainStore.unsubscribe(this)
    }
}