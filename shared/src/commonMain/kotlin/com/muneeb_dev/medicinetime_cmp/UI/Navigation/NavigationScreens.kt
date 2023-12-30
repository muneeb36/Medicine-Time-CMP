package com.muneeb_dev.medicinetime_cmp.UI.Navigation

import dev.icerock.moko.resources.ImageResource

sealed class NavigationScreens(
    val route:String,
    val objectName1: String = "",
    val objectPath: String = "",
) {
    object Splash: NavigationScreens(route = "screen_splash")
    object Home: NavigationScreens(route = "screen_home")
    object Intro: NavigationScreens(route = "screen_intro")
    object ConfirmName: NavigationScreens(route = "screen_confirm_name")
    object MyMedicines: NavigationScreens(route = "screen_mymedicines")
    object NewMedicine: NavigationScreens(route = "screen_newmedicine")
    object ToStart: NavigationScreens(route = "screen_to_start", objectName1 = "username" , objectPath = "/{username}")
    object MainScreen: NavigationScreens(route = "main_screen", objectName1 = "current_screen" , objectPath = "/{current_screen}")
    object MedicineDetails_Screen: NavigationScreens(route = "medicinedetails_screen", objectName1 = "medicinename" , objectPath = "/{medicinename}")

}