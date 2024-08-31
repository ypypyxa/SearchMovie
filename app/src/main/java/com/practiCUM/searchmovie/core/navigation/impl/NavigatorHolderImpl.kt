package com.practiCUM.searchmovie.core.navigation.impl

import androidx.fragment.app.Fragment
import com.practiCUM.searchmovie.core.navigation.api.Navigator
import com.practiCUM.searchmovie.core.navigation.api.NavigatorHolder

class NavigatorHolderImpl : NavigatorHolder {

    private var navigator: Navigator? = null

    override fun attachNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    override fun detachNavigator() {
        this.navigator = null
    }

    override fun openFragment(fragment: Fragment) {
        navigator?.openFragment(fragment)
    }

}