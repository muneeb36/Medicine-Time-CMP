package com.muneeb_dev.medicinetime_cmp.UI.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muneeb_dev.medicinetime_cmp.SharedRes
import com.muneeb_dev.medicinetime_cmp.Tools.AnimateVisibility
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools
import com.muneeb_dev.medicinetime_cmp.UI.Navigation.MyRoute
import com.muneeb_dev.medicinetime_cmp.UI.Navigation.NavigationScreens
import com.muneeb_dev.medicinetime_cmp.UI.UIComponents.BottomMenuBar
import com.muneeb_dev.medicinetime_cmp.UI.UIComponents.menuItems
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.compose.colorResource
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun MainScreen(navigator: Navigator, current_screen: String?) {
    var visible by remember { mutableStateOf(true) }
    var routeState = remember { mutableStateOf(MyRoute(NavigationScreens.NewMedicine.route)) }
    var color: ColorResource = remember { SharedRes.colors.primaryColor }
    var currentScreen by remember { mutableStateOf(routeState.value.screen_route) }
    MyTools.show_N_hide_statusbar(false)

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(0.dp, 0.dp, 0.dp, 56.dp)
                .background(colorResource(color))
        )
        {
            when (currentScreen) {
                NavigationScreens.MyMedicines.route -> {
                    MyMedicines_Screen("My", navigator)
                }

                NavigationScreens.NewMedicine.route -> {
                    NewMedicines_Screen(navigator)
                }
            }
        }
        AnimateVisibility(
            visible = visible,
            modifier = Modifier
                .wrapContentSize(Alignment.BottomStart)
        ) {
            BottomMenuBar(
                modifier = Modifier.height(56.dp),
                menuItems = menuItems,
                route = routeState
            )
            { menuItems, routeStateeee ->
                currentScreen = routeStateeee
            }
        }
    }
}