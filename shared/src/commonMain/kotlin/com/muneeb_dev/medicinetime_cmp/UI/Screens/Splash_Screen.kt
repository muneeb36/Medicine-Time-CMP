package com.muneeb_dev.medicinetime_cmp.UI.Screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.delay
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.getKoin

@Composable
fun Splash_Screen(
    navigator: Navigator,
) {
    MyTools.show_N_hide_statusbar(false)
    ImageWithFadeInText("Medicine\nTime", navigator)
}

@Composable
fun ImageWithFadeInText(
    text: String,
    navigator: Navigator,
    preferenceManager: PreferenceManager = getKoin().get()

) {
    val fadeInAnimation = rememberInfiniteTransition()
    val fadeInAlpha by fadeInAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
            },
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C76AA)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier.size(200.dp).alpha(fadeInAlpha),
                painter = painterResource(SharedRes.images.med_icon),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = text,
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
            )
        }

        if (preferenceManager.getBoolean(PF_isUserFirstTime)) {
            val user_name = preferenceManager.getString(PF_userName)
            LaunchedEffect(Unit) {
                delay(5000L) // 5000 milliseconds = 5 seconds
                navigator.navigate(NavigationScreens.MainScreen.route.plus("/$user_name"))
            }
        } else {
            LaunchedEffect(Unit) {
                delay(5000L) // 5000 milliseconds = 5 seconds
                navigator.navigate(
                    route = NavigationScreens.Intro.route,
                    NavOptions(
                        popUpTo = PopUpTo(
                            route = NavigationScreens.Intro.route,
                            inclusive = true
                        ),
                        launchSingleTop = true
                    )
                )
            }
        }
    }

}


