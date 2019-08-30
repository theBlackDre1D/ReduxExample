package com.example.beesafeexample

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import com.example.beesafeexample.core.base.BaseActivity
import com.example.beesafeexample.managers.UserManager
import com.example.beesafeexample.redux.actions.LoginActions
import com.example.beesafeexample.redux.appReducer
import com.example.beesafeexample.redux.middleware.firebaseMiddleware
import com.example.beesafeexample.redux.routes.RootRoutable
import com.example.beesafeexample.redux.states.AppState
import com.example.beesafeexample.redux.states.AuthenticationState
import com.example.beesafeexample.redux.states.LoggedInState
import com.pixplicity.easyprefs.library.Prefs
import org.rekotlin.Store
import org.rekotlinrouter.NavigationState
import org.rekotlinrouter.Router
import java.lang.ref.WeakReference

var mainStore = Store(
    state = null,
    reducer = ::appReducer,
    middleware = arrayListOf(firebaseMiddleware))

private var mInstance: AppController? = null
var router: Router<AppState>? = null


class AppController: Application() {

    companion object {
        lateinit var currentActivity: WeakReference<BaseActivity>
            private set

        @get:Synchronized var instance: AppController? = null
            private set

        fun getAppController(context: Context): AppController {
            if (instance == null) instance = AppController()
            return instance as AppController
        }
    }

    override fun onCreate() {
        super.onCreate()

        initPrefs()
        registerCallbacks()

        mInstance = this
        instance = this

        val loginState = getLogedInState()
        val authenticationState = AuthenticationState(loginState.loggedInState, loginState.userName)
        val state = AppState(NavigationState(), authenticationState)

        mainStore = Store(
            state = state,
            reducer = ::appReducer,
            middleware = arrayListOf(firebaseMiddleware),
            automaticallySkipRepeats = true
        )

        router = Router(
            store = mainStore,
            rootRoutable = RootRoutable(applicationContext),
            stateTransform = { subscription ->
                subscription.select { stateType -> stateType.navigationState }
            }
        )
    }

    private fun getLogedInState(): LoginActions.LoggedInDataSave {
        val loggedIn = if (UserManager.isLogin) LoggedInState.LOG_IN else LoggedInState.NOT_LOG_IN
        val userName = UserManager.userName
        return LoginActions.LoggedInDataSave(
            userName ?: "", loggedIn)
    }

    private fun initPrefs() {
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    private fun registerCallbacks() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {}

            override fun onActivityResumed(activity: Activity?) {}

            override fun onActivityStarted(activity: Activity?) {}

            override fun onActivityDestroyed(activity: Activity?) {}

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

            override fun onActivityStopped(activity: Activity?) {}

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                if (activity is BaseActivity) currentActivity = WeakReference(activity)
            }

        })
    }
}