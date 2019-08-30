package com.example.beesafeexample.redux.actions

import com.example.beesafeexample.redux.states.LoggedInState
import org.rekotlin.Action

sealed class LoginActions {
    data class LoginAction(val userName: String, val password: String): Action
    data class LoginStarted(val userName: String): Action
    data class LoginCompletedAction(val userName: String): Action

    data class LoggedInDataSaveAction(
        val userName: String,
        val loggedInState: LoggedInState): Action
}