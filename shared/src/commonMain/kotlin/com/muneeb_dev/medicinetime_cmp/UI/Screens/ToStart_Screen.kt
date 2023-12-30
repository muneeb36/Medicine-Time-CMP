package com.muneeb_dev.medicinetime_cmp.UI.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.muneeb_dev.medicinetime_cmp.Tools.MyConstants.Companion.PF_isUserFirstTime
import com.muneeb_dev.medicinetime_cmp.Tools.MyConstants.Companion.PF_userName
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools
import com.muneeb_dev.medicinetime_cmp.Tools.PreferenceManager
import com.muneeb_dev.medicinetime_cmp.UI.Navigation.NavigationScreens
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.getKoin

@Composable
fun ToStart_Screen(
    navigator: Navigator,
    user_name: String?,
    preferenceManager: PreferenceManager = getKoin().get(),
) {

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

            Image(
                modifier = Modifier.height(400.dp).width(300.dp),
                painter = painterResource(SharedRes.images.nurse),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Very good !",
                color = colorResource(SharedRes.colors.textColor_main),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = user_name.toString(),
                color = colorResource(SharedRes.colors.textColor_main),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.width(200.dp),
                maxLines = 3,
                text = "Now let's start taking care of your medicine.",
                color = colorResource(SharedRes.colors.textColor_main),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    preferenceManager.setBoolean(PF_isUserFirstTime, true)
                    if (user_name != null) {
                        preferenceManager.setString(PF_userName, user_name)
                    }
                    navigator.navigate(NavigationScreens.MainScreen.route.plus("/$user_name"))
                },
                modifier = Modifier
                    .width(231.dp)
                    .height(56.dp)
                    .padding(horizontal = 20.dp)
                    .then(Modifier.clip(shape = RoundedCornerShape(16.dp)))
                    .background(colorResource(SharedRes.colors.ok_button_color)),

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0.11f, 0.46f, 0.67f),
                    disabledBackgroundColor = Color(0.11f, 0.46f, 0.67f)
                ),

                ) {
                Text("Start", fontSize = 18.sp, color = Color.White)
            }

        }

    }
}