package com.practiCUM.searchmovie.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practiCUM.searchmovie.databinding.ActivityRootBinding
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.ui.movies.MoviesFragment
import com.practiCUM.searchmovie.core.navigation.api.NavigatorHolder
import com.practiCUM.searchmovie.core.navigation.impl.NavigatorImpl
import org.koin.android.ext.android.inject



class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    // Заинжектили NavigatorHolder,
    // чтобы прикрепить к нему Navigator
    private val navigatorHolder: NavigatorHolder by inject()

    // Создали Navigator
    private val navigator = NavigatorImpl(
        fragmentContainerViewId = R.id.rootFragmentContainerView,
        fragmentManager = supportFragmentManager
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Привязываем вёрстку к экрану
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            // С помощью навигатора открываем первый экран
            navigator.openFragment(
                MoviesFragment()
            )
        }
    }

    // Прикрепляем Navigator к NavigatorHolder
    override fun onResume() {
        super.onResume()
        navigatorHolder.attachNavigator(navigator)
    }

    // Открепляем Navigator от NavigatorHolder
    override fun onPause() {
        super.onPause()
        navigatorHolder.detachNavigator()
    }
}