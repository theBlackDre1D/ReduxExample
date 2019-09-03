package com.example.beesafeexample.ui.activities

import android.os.Bundle
import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseActivity
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.redux.routes.loginActivityRoute
import com.example.beesafeexample.redux.routes.loginFragmentRoute
import com.example.beesafeexample.redux.routes.mainActivityRoute
import org.rekotlinrouter.SetRouteAction

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val routes= arrayListOf(mainActivityRoute, loginActivityRoute, loginFragmentRoute)
        val action = SetRouteAction(routes)
        mainStore.dispatch(action)
    }
}
