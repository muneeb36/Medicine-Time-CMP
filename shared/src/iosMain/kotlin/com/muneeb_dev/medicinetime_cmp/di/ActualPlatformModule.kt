package com.muneeb_dev.medicinetime_cmp.di

import com.muneeb_dev.medicinetime_cmp.Expect_.MultiplatformSetting
import com.muneeb_dev.medicinetime_cmp.Expect_.DatabaseDriverFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        DatabaseDriverFactory()
    }
    factory { MultiplatformSetting() }

}