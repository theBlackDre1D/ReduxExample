package com.example.beesafeexample.redux.states

import com.example.beesafeexample.models.User
import org.rekotlin.StateType
import org.rekotlinrouter.HasNavigationState
import org.rekotlinrouter.NavigationState


data class AuthenticationState(
    var loggedInState: LoggedInState,
    var userName: String,
    var isFetching: Boolean = false,
    var isCompleted: Boolean = false
): StateType

enum class LoggedInState {
    NOT_LOG_IN,
    LOG_IN
}

data class AppState(
    override var navigationState: NavigationState,
    var authenticationState: AuthenticationState,
    var documentsState: DocumentState
): StateType, HasNavigationState

data class DocumentState(
    var documents: List<User>? = null,
    var isFetching: Boolean = true
): StateType

