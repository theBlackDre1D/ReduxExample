package com.example.beesafeexample.redux

import com.example.beesafeexample.redux.actions.LoggedUserActions
import com.example.beesafeexample.redux.actions.LoginActions
import com.example.beesafeexample.redux.states.AppState
import com.example.beesafeexample.redux.states.AuthenticationState
import com.example.beesafeexample.redux.states.DocumentState
import com.example.beesafeexample.redux.states.LoggedInState
import org.rekotlin.Action
import org.rekotlinrouter.NavigationReducer


fun appReducer(action: Action, oldState: AppState?): AppState {
    val state = oldState ?: AppState(
        navigationState = NavigationReducer.handleAction(action, oldState?.navigationState),
        authenticationState = AuthenticationState(LoggedInState.LOG_IN, userName = ""),
        documentsState = DocumentState()
    )

    return state.copy(
        navigationState = NavigationReducer.reduce(action, state.navigationState),
        authenticationState = (::authenticationReducer)(action, state.authenticationState),
        documentsState = (::documentReducer)(action, state.documentsState)
    )
}

fun authenticationReducer(action: Action,
                          state: AuthenticationState?): AuthenticationState {

    val newState = state ?: AuthenticationState(LoggedInState.NOT_LOG_IN, "")
    when(action) {
        is LoginActions.RequestSMSCode -> return newState.copy(isFetching = true)
        is LoginActions.VerifySMSCode -> return newState.copy(isFetching = true)
        is LoginActions.LoginStarted -> return newState.copy(isFetching = true)
        is LoginActions.WaitingForCode -> return newState.copy(isFetching = false)
        is LoginActions.LoginCompleted -> {
            return newState.copy(
                isFetching = false,
                loggedInState = LoggedInState.LOG_IN
            )
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

fun documentReducer(action: Action, state: DocumentState?): DocumentState {
    val newState = state ?: DocumentState()
    when (action) {
        is LoggedUserActions.PopulateDB -> return newState
        is LoggedUserActions.ShowContent -> return newState.copy(
            isFetching = true
        )
        is LoggedUserActions.DocumentsFetched -> return newState.copy(
            isFetching = false,
            documents = action.documents
        )
    }

    return newState
}