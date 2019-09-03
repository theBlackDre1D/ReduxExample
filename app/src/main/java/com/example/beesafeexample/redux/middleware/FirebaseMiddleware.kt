package com.example.beesafeexample.redux.middleware

import com.example.beesafeexample.Utils.FirestoreUtils
import com.example.beesafeexample.Utils.LoginUtils
import com.example.beesafeexample.redux.actions.LoggedUserActions
import com.example.beesafeexample.redux.actions.LoginActions
import com.example.beesafeexample.redux.states.AppState
import org.rekotlin.Middleware

internal val firebaseMiddleware: Middleware<AppState> = { dispatch, getState ->
    { next ->
        { action ->
            when(action) {
                is LoginActions.RequestSMSCode -> LoginUtils.setPhoneAuthentification(action.number)
                is LoginActions.VerifySMSCode -> LoginUtils.verifySMSCode(action.code)
                is LoggedUserActions.ShowContent -> FirestoreUtils.readUsers(action.db)
                is LoggedUserActions.PopulateDB -> FirestoreUtils.addUser(action.db, action.user)

                // can add more of them
            }
            next(action)
        }
    }
}