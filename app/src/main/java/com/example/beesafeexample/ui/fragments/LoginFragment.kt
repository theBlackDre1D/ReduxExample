package com.example.beesafeexample.ui.fragments

import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseInputFragment
import com.example.beesafeexample.core.extension.getFullText
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.redux.actions.LoginActions
import com.example.beesafeexample.redux.routes.loginActivityRoute
import com.example.beesafeexample.redux.routes.loginFragmentRoute
import com.example.beesafeexample.redux.routes.mainActivityRoute
import com.example.beesafeexample.redux.routes.userContentRoute
import com.example.beesafeexample.redux.states.AppState
import com.example.beesafeexample.redux.states.AuthenticationState
import com.example.beesafeexample.redux.states.LoggedInState
import kotlinx.android.synthetic.main.fragment_login.*
import org.rekotlin.StoreSubscriber
import org.rekotlinrouter.SetRouteAction

class LoginFragment(override var params: Params?
): BaseInputFragment<LoginFragment.Params>(params) {

    data class Params(
        val onNext: () -> Unit
    ): BaseParams()

    override val layoutResource: Int
        get() = R.layout.fragment_login



    override fun setupUI() {
        super.setupUI()

        setupButtons()
    }

    private fun setupButtons() {
        bGetCode.setOnClickListener {
            mainStore.dispatch(LoginActions.RequestSMSCode(etPhoneNumber.getFullText()))
        }

        bLogin.setOnClickListener {
            mainStore.dispatch(LoginActions.VerifySMSCode(etCode.getFullText()))
        }
    }

    override fun newState(state: AppState) {
        showOrHideLoading(state.authenticationState.isFetching)
        if (state.authenticationState.loggedInState == LoggedInState.LOG_IN) navigateToUserDetail()
    }


    private fun navigateToUserDetail() {
        val routes= arrayListOf(mainActivityRoute, userContentRoute)
        val action = SetRouteAction(routes)
        mainStore.dispatch(action)
    }

}