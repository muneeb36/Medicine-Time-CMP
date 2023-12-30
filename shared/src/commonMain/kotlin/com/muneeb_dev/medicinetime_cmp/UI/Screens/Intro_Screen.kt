package com.muneeb_dev.medicinetime_cmp.UI.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muneeb_dev.medicinetime_cmp.SharedRes
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools
import com.muneeb_dev.medicinetime_cmp.UI.Navigation.NavigationScreens
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun Intro_Screen(navigator: Navigator) {

    MyTools.show_N_hide_statusbar(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Your Remedies\non the right time\nin an easy way.\n",
                color = colorResource(SharedRes.colors.textColor_main),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
            )


            Image(
                modifier = Modifier.size(300.dp),
                painter = painterResource(SharedRes.images.drug_capsule),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.width(200.dp),
                maxLines = 3,
                text = "Don't forget to take your medicines anymore. We help you control your medications and when to take them.",
                color = colorResource(SharedRes.colors.textColor_main),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .width(110.dp)
                    .height(60.dp)
                    .padding(horizontal = 20.dp)
                    .then(Modifier.clip(shape = RoundedCornerShape(16.dp)))
                    .background(Color(0xFF1C76AA))
                    .clickable { navigator.navigate(NavigationScreens.ConfirmName.route) },
                contentAlignment = Alignment.Center
            ) {
                // Content of your rounded corner box
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(SharedRes.images.next_arrow),
                    contentDescription = null,

                    )
            }
        }

    }
}