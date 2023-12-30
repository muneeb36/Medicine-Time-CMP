package com.muneeb_dev.medicinetime_cmp.Tools

import com.muneeb_dev.medicinetime_cmp.UI.UIComponents.Menu
import dev.icerock.moko.resources.ImageResource

data class MenuItem(
    val item: Menu,
    val title: String,
    val icon: ImageResource,
    val screen: String,
)