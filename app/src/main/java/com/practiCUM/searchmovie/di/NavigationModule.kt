package com.practiCUM.searchmovie.di

import com.practiCUM.searchmovie.core.navigation.api.Router
import com.practiCUM.searchmovie.core.navigation.impl.RouterImpl
import org.koin.dsl.module

val navigationModule = module {
    val router = RouterImpl()

    single<Router> { router }
    single { router.navigatorHolder }
}