package com.practiCUM.searchmovie.core.navigation.impl

import androidx.fragment.app.Fragment
import com.practiCUM.searchmovie.core.navigation.api.NavigatorHolder
import com.practiCUM.searchmovie.core.navigation.api.Router

class RouterImpl : Router {

    override val navigatorHolder: NavigatorHolder = NavigatorHolderImpl()

    override fun openFragment(fragment: Fragment) {
        navigatorHolder.openFragment(fragment)
    }

}