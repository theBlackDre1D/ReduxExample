package com.example.beesafeexample.redux.actions

import com.example.beesafeexample.models.User
import com.google.firebase.firestore.FirebaseFirestore
import org.rekotlin.Action

sealed class LoggedUserActions {
    data class ShowContent(val db: FirebaseFirestore): Action
    data class PopulateDB(
        val db: FirebaseFirestore,
        val user: HashMap<String, Any>): Action
    data class DocumentsFetched(val documents: List<User>): Action
}