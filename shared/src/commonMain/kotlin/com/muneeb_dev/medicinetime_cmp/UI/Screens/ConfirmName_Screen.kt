package com.muneeb_dev.medicinetime_cmp.UI.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
fun ConfirmName_Screen(navigator: Navigator) {

    MyTools.show_N_hide_statusbar(false)

    var text by remember { mutableStateOf("") }
    var isTextEmpty by remember { mutableStateOf(true) }
    var errorText by remember { mutableStateOf("") }


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
                modifier = Modifier.size(100.dp),
                painter = painterResource(SharedRes.images.doc),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.width(250.dp),
                maxLines = 1,
                text = "What is your name ?",
                color = colorResource(SharedRes.colors.textColor_main),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Center )
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = text,
                onValueChange = {
                    text = it
                    isTextEmpty = it.isEmpty()
                    errorText = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal =80.dp, vertical = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle the done action if needed
                        if (text.isEmpty()) {
                            errorText = "Text field cannot be empty"
                        } else {
                            // Handle the button click
                            navigator.navigate(NavigationScreens.ToStart.route.plus("/$text"))
                        }
                    }
                ),
                placeholder = {
                    Text(
                        "Enter Name here",
                        fontSize = 18.sp,
                        color = Color.Gray,
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }, // Placeholder text
                singleLine = true,
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp, textAlign = TextAlign.Center),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent, // Background color
                    cursorColor = Color(0xFF1C76AA), //Color.Black, // Cursor color
                    focusedIndicatorColor = Color(0xFF1C76AA), //Color.Transparent, // Removes the bottom line when focused
                    unfocusedIndicatorColor = Color.Black // Removes the bottom line when not focused
                ),
            )


            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    // Handle the button click
                    if (text.isEmpty()) {
                        errorText = "Text field cannot be empty"
                    } else {
                        // Handle the button click
                        navigator.navigate(NavigationScreens.ToStart.route.plus("/$text"))
                    }
                },
                modifier = Modifier
                    .width(231.dp)
                    .height(56.dp)
                    .padding(horizontal = 20.dp)
                    .then(Modifier.clip(shape = RoundedCornerShape(16.dp))),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(SharedRes.colors.ok_button_color),
                    disabledBackgroundColor = Color.LightGray),
                enabled = !isTextEmpty
            ) {
                Text("Confirm", fontSize = 18.sp, color =  Color.White)
            }
        }
    }
}