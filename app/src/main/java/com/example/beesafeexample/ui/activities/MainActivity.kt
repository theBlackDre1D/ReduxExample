package com.example.beesafeexample.ui.activities

import android.os.Bundle
import android.os.UserManager
import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseActivity
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.redux.routes.loginActivityRoute
import com.example.beesafeexample.redux.routes.mainActivityRoute
import com.example.beesafeexample.redux.routes.userContentRoute
import com.example.beesafeexample.redux.states.LoggedInState
import org.rekotlinrouter.SetRouteAction

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (mainStore.state.authenticationState.loggedInState == LoggedInState.LOG_IN) {
            val routes = arrayListOf(mainActivityRoute, userContentRoute)
            val action = SetRouteAction(routes)
            mainStore.dispatch(action)
        } else {
            val routes = arrayListOf(mainActivityRoute, loginActivityRoute)
            val action = SetRouteAction(routes)
            mainStore.dispatch(action)
        }
    }
}
