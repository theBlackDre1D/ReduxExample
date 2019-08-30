package com.example.beesafeexample.ui

import android.os.Bundle
import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseActivity
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.redux.routes.loginActivityRoute
import com.example.beesafeexample.redux.routes.mainActivityRoute
import org.rekotlinrouter.SetRouteAction

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val routes = arrayListOf(mainActivityRoute, loginActivityRoute)
        val action = SetRouteAction(routes)
        mainStore.dispatch(action)
    }
}
