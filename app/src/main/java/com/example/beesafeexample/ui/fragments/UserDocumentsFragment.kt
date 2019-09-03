package com.example.beesafeexample.ui.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beesafeexample.R
import com.example.beesafeexample.core.base.BaseFragment
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.models.User
import com.example.beesafeexample.redux.actions.LoggedUserActions
import com.example.beesafeexample.redux.states.AppState
import com.example.beesafeexample.ui.adapters.DocumentAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_user_documents.*
import org.rekotlin.StoreSubscriber

class UserDocumentsFragment: BaseFragment(), StoreSubscriber<AppState> {

    override val layoutResource: Int
        get() = R.layout.fragment_user_documents

    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }


    override fun onDestroy() {
        super.onDestroy()
        mainStore.unsubscribe(this)
    }
    override fun setupUI() {
        super.setupUI()

        mainStore.dispatch(LoggedUserActions.ShowContent(db))
    }


    override fun newState(state: AppState) {
        state.documentsState.documents?.let { setupDocuments(it) }
    }

    private fun setupDocuments(users: List<User>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = DocumentAdapter(users)
    }

}