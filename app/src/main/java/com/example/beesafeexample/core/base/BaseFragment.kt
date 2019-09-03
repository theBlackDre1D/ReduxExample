package com.example.beesafeexample.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.beesafeexample.core.extension.visibleOrGone
import com.example.beesafeexample.mainStore
import com.example.beesafeexample.redux.states.AppState
import kotlinx.android.synthetic.main.activity_main.*
import org.rekotlin.StoreSubscriber

abstract class BaseFragment: Fragment(), StoreSubscriber<AppState> {

    abstract val layoutResource: Int
    protected val activity: BaseActivity
        get() = getActivity() as BaseActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelsObservers()
        setupUI()
        setupSubscription()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainStore.unsubscribe(this)
    }

    open fun setupUI() {
        setupStoreSubscribers()
    }

    private fun setupSubscription() { mainStore.subscribe(this) }

    open fun setupViewModelsObservers() {}
    open fun setupStoreSubscribers() {}

    fun showOrHideLoading(visible: Boolean = true) { activity.loadingContainer.visibleOrGone(visible) }

}