package com.example.beesafeexample.redux.states

import org.rekotlin.StateType
import org.rekotlinrouter.NavigationState


data class AuthenticationState(
    var loggedInState: LoggedInState,
    var userName: String,
    var isFetching: Boolean = false,
    var isCompleted: Boolean = false,
    var errorMessage: String? = null,
    var fullName: String? = null,
    var location:String? = null,
    var avatarUrl:String? = null,
    var createdAt: String? = null
): StateType

enum class LoggedInState {
    NOT_LOG_IN,
    LOG_IN
}

data class AppState(
    override var navigationState: NavigationState,
    var authenticationState: AuthenticationState
): StateType, HasNavigationState


interface HasNavigationState {
    var navigationState: NavigationState
}

