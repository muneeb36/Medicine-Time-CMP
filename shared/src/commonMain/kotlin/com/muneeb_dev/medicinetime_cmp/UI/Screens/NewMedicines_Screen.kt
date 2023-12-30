package com.muneeb_dev.medicinetime_cmp.UI.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.muneeb_dev.medicinetime_cmp.SharedRes
import com.muneeb_dev.medicinetime_cmp.Tools.MyConstants
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools.medicineList
import com.muneeb_dev.medicinetime_cmp.Tools.PreferenceManager
import com.muneeb_dev.medicinetime_cmp.UI.Models.Medicine_Model
import com.muneeb_dev.medicinetime_cmp.UI.Navigation.NavigationScreens
import com.muneeb_dev.medicinetime_cmp.UI.UIComponents.App_Header
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.getKoin


@Composable
fun NewMedicines_Screen(
    navigator: Navigator,
    preferenceManager: PreferenceManager = getKoin().get(),

    ) {
    MyTools.show_N_hide_statusbar(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(SharedRes.colors.white)),
    ) {
        Column(
        ) {
            val username = preferenceManager.getString(MyConstants.PF_userName)

            if (username != null) {
                App_Header(username, true)
            }
            Spacer(modifier = Modifier.size(20.dp))

            Spacer(modifier = Modifier.size(20.dp))
            LazyColumnWithGridView(navigator)
        }
    }

}


@Composable
fun LazyColumnWithGridView(navigator: Navigator) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(20.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(medicineList.size) { it ->

            GridItem(navigator, medicineModel = medicineList.get(it))
        }
    }

}

@Composable
fun GridItem(navigator: Navigator, medicineModel: Medicine_Model) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(25.dp)) // Apply corner shape here
            .background(colorResource(SharedRes.colors.item_bg_gray))
            .clickable {
                navigator.navigate(NavigationScreens.MedicineDetails_Screen.route.plus("/${medicineModel.name}"))
            }
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(medicineModel.image),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp, bottom = 8.dp)
        )
        Text(
            text = medicineModel.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}
