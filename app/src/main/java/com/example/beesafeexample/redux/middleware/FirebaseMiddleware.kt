package com.example.beesafeexample.redux.middleware

import com.example.beesafeexample.Utils.LoginUtils
import com.example.beesafeexample.redux.actions.LoginActions
import com.example.beesafeexample.redux.states.AppState
import org.rekotlin.Middleware

internal val firebaseMiddleware: Middleware<AppState> = { dispatch, getState ->
    { next ->
        { action ->
            when(action) {
                is LoginActions.RequestSMSCode -> {
                    LoginUtils.setPhoneAuthentification(action.number)
                }
                // can add more of them
            }
            next(action)
        }
    }
}