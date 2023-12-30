package com.muneeb_dev.medicinetime_cmp.Expect_

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

actual class MultiplatformSetting(private val context: Context) {
    actual fun provideSettings(): ObservableSettings {
        val sharedPreferences =
            context.getSharedPreferences("app_preference", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(delegate = sharedPreferences)
    }
}