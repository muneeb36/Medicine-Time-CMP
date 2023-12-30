package com.muneeb_dev.medicinetime_cmp.android

import android.app.Application
import com.muneeb_dev.medicinetime_cmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class BaseApplication(): Application()
{
    override fun onCreate() {
        super.onCreate()

        initKoin{
            androidContext(this@BaseApplication)
        }
    }
}