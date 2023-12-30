package com.muneeb_dev.medicinetime_cmp.UI.UIComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muneeb_dev.medicinetime_cmp.SharedRes
import dev.icerock.moko.resources.compose.fontFamilyResource

@Composable
fun App_Header(currentScreen: String, isUserName: Boolean) {
    Box(modifier = Modifier.padding(20.dp))
    {
        Column {
            Text(
                text = if (isUserName) "Hello" else currentScreen,
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 36.sp,
                    fontFamily = fontFamilyResource(SharedRes.fonts.roboto_bold.roboto_bold),
                    fontWeight = FontWeight(100),
                    color = Color(0xFF141750),
                )
            )
            Text(
                text = if (isUserName) currentScreen else "Medicines",
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 36.sp,
                    fontFamily = fontFamilyResource(SharedRes.fonts.roboto_bold.roboto_bold),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF141750),
                )
            )
        }
    }
}