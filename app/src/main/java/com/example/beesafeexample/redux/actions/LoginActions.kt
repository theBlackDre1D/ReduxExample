package com.example.beesafeexample.redux.actions

import com.example.beesafeexample.redux.states.LoggedInState
import org.rekotlin.Action

sealed class LoginActions {
    data class LoginStarted(val userName: String): Action
    data class LoginCompleted(val userName: String): Action
    data class LoginFailed(val errorMessage: String): Action
    data class RequestSMSCode(val number: String): Action
    data class VerifySMSCode(val code: String): Action
    class WaitingForCode: Action

    data class LoggedInDataSave(
        val userName: String,
        val loggedInState: LoggedInState): Action
}