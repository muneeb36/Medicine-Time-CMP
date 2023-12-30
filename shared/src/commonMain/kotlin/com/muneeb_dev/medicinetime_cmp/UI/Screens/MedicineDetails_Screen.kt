package com.muneeb_dev.medicinetime_cmp.UI.Screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.muneeb_dev.medicinetime_cmp.Expect_.DatabaseDriverFactory
import com.muneeb_dev.medicinetime_cmp.Expect_.DeviceType
import com.muneeb_dev.medicinetime_cmp.SharedRes
import com.muneeb_dev.medicinetime_cmp.Tools.CustomButton
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools.getFormattedTime
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools.getImage
import com.muneeb_dev.medicinetime_cmp.Tools.gradientColors
import com.muneeb_dev.medicinetime_cmp.database.MedicineDatabase
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.mp.KoinPlatform.getKoin

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalResourceApi::class
)
@Composable
fun MedicineDetails_Screen(
    navigator: Navigator,
    medicineName: String,
    getDevice: DeviceType = com.muneeb_dev.medicinetime_cmp.Expect_.getDevice(),
    factory: DatabaseDriverFactory = getKoin().get()

) {
    var textValue2 by remember { mutableStateOf("What is the dosage of your medicine?") }
    var dosage_qty by remember { mutableStateOf(0) }

    val database = MedicineDatabase(factory.createDriver())

    val medicineQueries = database.mediciene_sqlQueries

    medicineQueries.createTable()



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                Modifier.fillMaxSize().weight(1f).background(
                    brush = Brush.verticalGradient(
                        colors = gradientColors,
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )

            ) {

            }

            Column(
                Modifier.fillMaxSize().weight(1f).background(White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Choose the best time to be reminded:",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 23.sp,
                        fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF141750),

                        textAlign = TextAlign.Center,
                    )
                )

                Spacer(Modifier.height(50.dp))

                val now = Clock.System.now()
                val dateTime = now

                val TimeSelection = remember { mutableStateOf("Click here to Select Time") }

                val dialogState = remember { mutableStateOf(false) }
                val timePicker = remember {
                    TimePickerState(
                        initialHour = dateTime.toLocalDateTime(TimeZone.currentSystemDefault()).hour,
                        initialMinute = dateTime.toLocalDateTime(TimeZone.currentSystemDefault()).minute,
                        is24Hour = false,
                    )
                }

                // Function to show/hide the dialog
                val showDialog = { dialogState.value = true }
                val hideDialog = {
                    dialogState.value = false
                    TimeSelection.value = getFormattedTime(
                        timePicker.hour,
                        timePicker.minute,
                        timePicker.is24hour,
                        1
                    )
                }


                Box(
                    Modifier
                        .fillMaxWidth().height(50.dp)
                        .padding(horizontal = 50.dp)
                        .background(
                            color = Color(0xFFE6F1FA),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .clickable { showDialog() }
                ) {

                    // Content inside the shape
                    Text(
                        text = TimeSelection.value,
                        modifier = Modifier.align(Alignment.Center),
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 23.sp,
                            fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF3D7199),
                            textAlign = TextAlign.Center,
                        )
                    )

                    if (dialogState.value) {
                        TimePickerDialog(timePicker) {
                            hideDialog()
                        }
                    }

                }

                Spacer(Modifier.height(50.dp))

                CustomButton(
                    modifier = Modifier
                        .size(width = 311.dp, height = 56.dp)
                        .clickable {
                            medicineQueries.insert(
                                medicineName, dateTime.epochSeconds, getFormattedTime(
                                    timePicker.hour,
                                    timePicker.minute,
                                    timePicker.is24hour,
                                    2
                                ), dosage_qty.toString().toLong()
                            )
                            navigator.goBack()
                        }
                        .background(
                            color = Color(0.11f, 0.46f, 0.67f),
                            shape = RoundedCornerShape(16.dp)
                        ), "Register Medicine"
                )

            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            val dialogState2 = remember { mutableStateOf(false) }
            // Function to show/hide the dialog
            val showDialog = { dialogState2.value = true }
            val hideDialog = { dialogState2.value = false }
            Box(
                Modifier
                    .fillMaxWidth().height(88.dp)
                    .padding(horizontal = 30.dp)
                    .background(
                        color = Color(0xFFE6F1FA),
                        shape = RoundedCornerShape(size = 20.dp)
                    ).clickable { showDialog() }
            ) {
                // Content inside the shape
                Text(
                    text = textValue2,
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(
                        fontSize = if (textValue2 == "What is the dosage of your medicine?") 15.sp else 28.sp,
                        lineHeight = 23.sp,
                        fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF3D7199),

                        textAlign = TextAlign.Center,
                    )
                )

            }

            if (dialogState2.value) {
                CustomDialog(onDismiss = {
                    hideDialog()
                }, Dosage = {
                    dosage_qty = it
                    textValue2 = it.toString() + " Tablets"
                })
            }
        }


        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier.padding(vertical = if (com.muneeb_dev.medicinetime_cmp.Expect_.getDevice().device == MyTools.currentDevice.iOS.toString()) 0.dp else 30.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Image(
                    painter = painterResource(SharedRes.images.backicon),
                    contentDescription = null, // Provide a suitable content description
                    modifier = Modifier.size(48.dp)
                        .clickable { navigator.goBack() } // Adjust the size as needed
                )


                Text(
                    text = "Back",
                    color = Color.Black,
                    modifier = Modifier.clickable { navigator.goBack() },
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Image(
                modifier = Modifier.fillMaxWidth().height(180.dp).padding(horizontal = 50.dp),
                painter = painterResource(imageResource = getImage(medicineName)),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(20.dp)) // Adjust spacing between drawable and text

            Text(
                text = medicineName,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                style = TextStyle(
                    fontSize = 26.sp,
                    lineHeight = 23.sp,
                    fontFamily = fontFamilyResource(SharedRes.fonts.roboto_bold.roboto_bold),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF141750),
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(10.dp)) // Adjust spacing between drawable and text

            Text(
                text = "For convulsions and old age, etc.",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 25.sp,
                    fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF141750),

                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}


@Composable
fun CustomDialog(onDismiss: () -> Unit, Dosage: (Int) -> Unit) {
    val animationDuration = 400
    val dialogHeight by animateDpAsState(
        targetValue = 300.dp,
        animationSpec = tween(durationMillis = animationDuration)
    )
    var textValue by remember { mutableStateOf(TextFieldValue()) }

    Dialog(
        onDismissRequest = { onDismiss.invoke() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(322.dp)
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 20.dp)
                )

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(30.dp, 0.dp)
                    .height(dialogHeight)
                    .fillMaxWidth()
                    .animateContentSize() // Animates size changes
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Enter Dosage",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF3D7199),
                        textAlign = TextAlign.Center,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = textValue,
                    onValueChange = { newTextFieldValue: TextFieldValue ->
                        textValue = newTextFieldValue
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),

                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    placeholder = {
                        Text(
                            "Enter numbers only",
                            fontSize = 18.sp,
                            color = Color.Gray,
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }, // Placeholder text
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent, // Background color
                        cursorColor = Color(0xFF1C76AA), //Color.Black, // Cursor color
                        focusedIndicatorColor = Color(0xFF1C76AA), //Color.Transparent, // Removes the bottom line when focused
                        unfocusedIndicatorColor = Color.Black // Removes the bottom line when not focused
                    ),
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0.11f, 0.46f, 0.67f),
                        disabledBackgroundColor = Color(0.11f, 0.46f, 0.67f)
                    ),
                    onClick = {
                        onDismiss.invoke()
                        Dosage.invoke(textValue.text.toString().toInt())
                    },
                    modifier = Modifier.fillMaxWidth()
                        .height(70.dp)
                        .padding(20.dp, 10.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Text(text = "OK", color = Color.White)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(timePickerstate: TimePickerState, onDismiss: () -> Unit) {
    val animationDuration = 400
    val dialogHeight by animateDpAsState(
        targetValue = 300.dp,
        animationSpec = tween(durationMillis = animationDuration)
    )

    Dialog(onDismissRequest = { onDismiss.invoke() }, properties = DialogProperties())
    {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp))

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize() // Animates size changes
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = "Select Time of your Dosage",
                        style = TextStyle(
                            fontSize = if (com.muneeb_dev.medicinetime_cmp.Expect_.getDevice().device == MyTools.currentDevice.iOS.toString()) 18.sp else 28.sp,
                            lineHeight = 23.sp,
                            fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF3D7199),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    TimePicker(
                        state = timePickerstate,
                        layoutType = TimePickerLayoutType.Vertical
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0.11f, 0.46f, 0.67f),
                            disabledBackgroundColor = Color(0.11f, 0.46f, 0.67f)
                        ),
                        onClick = { onDismiss.invoke() },
                        modifier = Modifier.fillMaxWidth()
                            .height(70.dp)
                            .padding(20.dp, 10.dp)
                            .clip(RoundedCornerShape(16.dp))

                    ) {
                        Text(text = "OK", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
    }
}
