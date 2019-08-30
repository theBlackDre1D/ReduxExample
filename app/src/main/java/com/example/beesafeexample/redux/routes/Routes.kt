package com.example.beesafeexample.redux.routes

import android.content.Context
import android.util.Log
import com.example.beesafeexample.ui.LoginFragment
import org.rekotlinrouter.Routable
import org.rekotlinrouter.RouteElementIdentifier
import org.rekotlinrouter.RoutingCompletionHandler


val loginActivityRoute: RouteElementIdentifier = "LoginActivity"
val mainActivityRoute: RouteElementIdentifier = "MainActivity"
val loginFragmentRoute: RouteElementIdentifier = "LoginFragment"


class RootRoutable(val context: Context): Routable {

    // ["Home", "User"] to ["Home", "Detail"]
    override fun changeRouteSegment(
        from: RouteElementIdentifier,
        to: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        when (to) {
            loginFragmentRoute -> return RoutableHelper.backStackFragmentRoutable(LoginFragment(LoginFragment.Params({  })))
        }

        return this
    }

    // ["Home", "User"] to ["Home"]
    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler
    ) {}

    // ["Home"] to ["Home", "User"]
    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler
    ): Routable {
        return MainActivityRouteble(context)
    }

}

class MainActivityRouteble(val context: Context): Routable{

    val TAG = this::class.java.simpleName

    override fun changeRouteSegment(
        from: RouteElementIdentifier,
        to: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ) {
        Log.d(TAG, "Popping route segment")
    }

    override fun pushRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        when (routeElementIdentifier) {
            loginActivityRoute -> return RoutableHelper.loginActivityRoutable(context)
        }

        return RoutableHelper.mainActivityRoutable(context)
    }

}

class LoginActivityRoutable(val context: Context): Routable {
    override fun changeRouteSegment(
        from: RouteElementIdentifier,
        to: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        when (routeElementIdentifier) {
            loginFragmentRoute -> return RoutableHelper.backStackFragmentRoutable(LoginFragment(LoginFragment.Params({  })))
        }

        return RoutableHelper.backStackFragmentRoutable(LoginFragment(LoginFragment.Params({  })))
    }

}

class FragmentRoutable(context: Context): Routable {

    val TAG = this::class.java.simpleName

    override fun changeRouteSegment(
        from: RouteElementIdentifier,
        to: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ) {
        Log.d(TAG,"FragmentRoutable popRouteSegment")
    }

    override fun pushRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}