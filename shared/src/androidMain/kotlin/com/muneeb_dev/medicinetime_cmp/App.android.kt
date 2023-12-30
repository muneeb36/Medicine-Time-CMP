package com.muneeb_dev.medicinetime_cmp

import androidx.compose.runtime.Composable

@Composable
fun MainAndroidView() = App()

//actual class PlatformModule actual constructor() {
//    actual fun platformModule(): Module {
//        // Provide the actual implementation for Android
//        return module {
//            // Define Android-specific dependencies
//            // For example:
//            // single { MyAndroidService() }
//            single { DB_di_android_module(get()) }
//
//        }
//    }
//}


//actual class SqlDriver_Platform actual constructor() {
//    val driver = DB_di_android_module(get)
//}

//class AndroidPlatform : Platform {
//    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
//}
//
//actual fun getPlatform(): Platform = AndroidPlatform()