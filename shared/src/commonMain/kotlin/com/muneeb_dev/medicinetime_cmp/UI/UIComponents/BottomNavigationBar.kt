package com.muneeb_dev.medicinetime_cmp.UI.UIComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muneeb_dev.medicinetime_cmp.SharedRes
import com.muneeb_dev.medicinetime_cmp.Tools.MenuItem
import com.muneeb_dev.medicinetime_cmp.UI.Navigation.MyRoute
import com.muneeb_dev.medicinetime_cmp.UI.Navigation.NavigationScreens
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource


val menuItems = arrayListOf<MenuItem>().apply {
    add(
        MenuItem(
            Menu.MyMedicines,
            "MyMedicine",
            SharedRes.images.list,
            NavigationScreens.MyMedicines.route
        )
    )
    add(
        MenuItem(
            Menu.NewMedicine,
            "NewMedicine",
            SharedRes.images.circle_plus,
            NavigationScreens.NewMedicine.route
        )
    )
}

enum class Menu(index: Int) {
    MyMedicines(0),
    NewMedicine(1),

}

@Composable
private fun BottomMenuItem(
    menuItem: MenuItem,
    route: MutableState<MyRoute>,
    onItemClicked: (MenuItem) -> Unit
) {
    var routeState by remember { mutableStateOf<String?>(NavigationScreens.MyMedicines.route) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
            .clickable {
                onItemClicked.invoke(menuItem)
                routeState = menuItem.screen

            }
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            painter = painterResource(menuItem.icon),
            contentDescription = menuItem.title,
            tint = colorResource(
                if (routeState == route.value.screen_route) SharedRes.colors.primaryColor else SharedRes.colors.secondTextColor
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                .align(Alignment.CenterVertically),
            text = menuItem.title,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 25.sp,
                fontWeight = FontWeight(400),
                color = colorResource(if (routeState == route.value.screen_route) SharedRes.colors.primaryColor else SharedRes.colors.secondTextColor),
                textAlign = TextAlign.Center,
            )
        )
        routeState = null

    }
}

@Composable
fun BottomMenuBar(
    modifier: Modifier = Modifier,
    route: MutableState<MyRoute>,
    menuItems: List<MenuItem>,
    onItemClicked: (MenuItem, route: String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(SharedRes.colors.white),
                shape = RoundedCornerShape(size = 0.dp)
            ),
        elevation = 20.dp

    ) {
        LazyRow(
            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(
                items = menuItems,
                itemContent = { item ->
                    BottomMenuItem(item, route) {
                        onItemClicked.invoke(it, it.screen)
                    }
                }
            )
        }
    }
}