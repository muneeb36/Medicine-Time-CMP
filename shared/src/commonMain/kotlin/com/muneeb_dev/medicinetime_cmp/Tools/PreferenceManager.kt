package com.muneeb_dev.medicinetime_cmp.Tools

import com.muneeb_dev.medicinetime_cmp.Expect_.MultiplatformSetting
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.set
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PreferenceManager : KoinComponent {

    private val multiplatformSetting: MultiplatformSetting by inject()

    private val observableSettings = multiplatformSetting.provideSettings()

    ////// String
    fun getString(key: String) = observableSettings.getStringOrNull(key = key)

    fun getStringAsFlow(key: String) = observableSettings.getStringOrNullFlow(key = key)

    fun setString(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }
    ////// int
    fun getInt(key: String) = observableSettings.getIntOrNull(key = key)

    fun setInt(key: String, value: Int) {
        observableSettings.set(key = key, value = value)
    }

    ////// Boolean
    fun getBoolean(key: String) = observableSettings.getBoolean(key = key , defaultValue = false)
    fun setBoolean(key: String , value: Boolean) {
        observableSettings.set(key = key , value = value)
    }
}
