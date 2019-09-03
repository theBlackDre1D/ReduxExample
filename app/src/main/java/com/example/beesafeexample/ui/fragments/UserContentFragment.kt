package com.example.beesafeexample.ui.fragments

import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseFragment
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.redux.actions.LoggedUserActions
import com.example.beesafeexample.redux.routes.mainActivityRoute
import com.example.beesafeexample.redux.routes.userDocumentsRoute
import com.example.beesafeexample.redux.states.AppState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_content.*
import org.rekotlin.StoreSubscriber
import org.rekotlinrouter.SetRouteAction

class UserContentFragment: BaseFragment(), StoreSubscriber<AppState> {

    override val layoutResource: Int
        get() = R.layout.fragment_content


    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override fun setupUI() {
        super.setupUI()

        bShowContent.setOnClickListener { navigateToContent() }

        bPopulateDatabase.setOnClickListener { populateDatabase() }
    }

    override fun newState(state: AppState) { }

    private fun populateDatabase() {
        val userOne = hashMapOf(
            "name" to "Michal",
            "surname" to "Gray",
            "age" to 23
        )
        val userTwo = hashMapOf(
            "name" to "Diana",
            "surname" to "Gray",
            "age" to 17
        )
        mainStore.dispatch(LoggedUserActions.PopulateDB(db, userOne))
        mainStore.dispatch(LoggedUserActions.PopulateDB(db, userTwo))
    }

    private fun navigateToContent() {
        val routes = arrayListOf(mainActivityRoute, userDocumentsRoute)
        val actions = SetRouteAction(routes)
        mainStore.dispatch(actions)
    }
}