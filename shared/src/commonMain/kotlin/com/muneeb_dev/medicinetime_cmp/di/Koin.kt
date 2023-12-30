package com.muneeb_dev.medicinetime_cmp.di


import com.muneeb_dev.medicinetime_cmp.Tools.PreferenceManager
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoinIOS(){
    val modules = listOf(
        platformModule(),
        commonModules
    )
    startKoin {
        modules
    }
    loadKoinModules(modules)
}

fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
    appDeclaration()
    val modules = listOf(
        platformModule(),
        commonModules
    )
    loadKoinModules(modules)
}

val commonModules = module {
    // for storage key-value like shared pref and UserDefaults
    single {  PreferenceManager() }
}

