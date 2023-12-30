package com.muneeb_dev.medicinetime_cmp.Expect_

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import platform.Foundation.NSUserDefaults

actual class MultiplatformSetting {
    actual fun provideSettings(): ObservableSettings {
        val nsUserDefault = NSUserDefaults.standardUserDefaults
        return NSUserDefaultsSettings(delegate = nsUserDefault)
    }
}