package com.example.beesafeexample.Utils

import com.example.beesafeexample.AppController
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.models.User
import com.example.beesafeexample.redux.actions.LoggedUserActions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

object FirestoreUtils {

    fun addUser(db: FirebaseFirestore, user: HashMap<String, Any>) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                AppController.currentActivity.get()!!.showSuccessToast("Document with ID: ${documentReference.id} added!")
            }
            .addOnFailureListener { AppController.currentActivity.get()!!.showErrorToast("Document was not added") }
    }

    fun readUsers(db: FirebaseFirestore) {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->convertToUsers(result) }
    }

    private fun convertToUsers(result: QuerySnapshot) {
        val users = mutableListOf<User>()
        result.forEach {
            val hashMap = it.data
            val newUser = User(
                hashMap["name"] as String,
                hashMap["surname"] as String,
                (hashMap["age"] as Long).toInt()
            )
            users.add(newUser)
        }

        mainStore.dispatch(LoggedUserActions.DocumentsFetched(users))
    }
}