package com.example.beesafeexample.redux

import com.example.beesafeexample.redux.actions.LoginActions
import com.example.beesafeexample.redux.states.AppState
import com.example.beesafeexample.redux.states.AuthenticationState
import com.example.beesafeexample.redux.states.LoggedInState
import org.rekotlin.Action
import org.rekotlinrouter.NavigationReducer


fun appReducer(action: Action, oldState: AppState?): AppState {
    val state = oldState ?: AppState(
        navigationState = NavigationReducer.handleAction(action, oldState?.navigationState),
        authenticationState = AuthenticationState(LoggedInState.LOG_IN, userName = ""))

    return state.copy(
        navigationState = NavigationReducer.reduce(action, state.navigationState),
        authenticationState = (::authenticationReducer)(action, state.authenticationState)
    )
}

fun authenticationReducer(action: Action,
                          state: AuthenticationState?): AuthenticationState {

    val newState = state ?: AuthenticationState(LoggedInState.NOT_LOG_IN, "")
    when(action) {
        is LoginActions.LoginStarted -> return newState.copy(isFetching = true)
        is LoginActions.LoginCompleted -> {
            return newState.copy(isFetching = false) // here can be added more attributes
        }
        is LoginActions.LoggedInDataSave -> {
            return newState.copy(
                isCompleted = true,
                isFetching = false,
                loggedInState = LoggedInState.LOG_IN)
        }
        is LoginActions.LoginFailed -> {
            return newState.copy(
                isFetching = false,
                isCompleted = true,
                loggedInState = LoggedInState.NOT_LOG_IN
            )
        }
    }
    return newState
}