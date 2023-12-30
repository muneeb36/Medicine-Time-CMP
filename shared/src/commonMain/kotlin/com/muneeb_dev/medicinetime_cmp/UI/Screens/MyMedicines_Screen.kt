package com.muneeb_dev.medicinetime_cmp.UI.Screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
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
import com.muneeb_dev.medicinetime_cmp.Tools.Draggable_item
import com.muneeb_dev.medicinetime_cmp.Tools.ITEM_OFFSET
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools
import com.muneeb_dev.medicinetime_cmp.UI.Navigation.NavigationScreens
import com.muneeb_dev.medicinetime_cmp.UI.UIComponents.App_Header
import com.muneeb_dev.medicinetime_cmp.VIewModels.Main_ViewModel
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.launch
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun MyMedicines_Screen(
    currentScreen: String,
    navigator: Navigator,
    viewModel: Main_ViewModel = Main_ViewModel()
) {
    MyTools.show_N_hide_statusbar(false)
    viewModel.getAllItems()
    val yourData by viewModel.allItems.collectAsState()
    val updatedData by rememberUpdatedState(newValue = yourData) // Remember only data changes


    val scope = rememberCoroutineScope()

    // Function to handle deletion of an item
    fun deletemyItem(itemId: String) {
        val itemToDelete = updatedData.firstOrNull { it.medicine_name == itemId }
        if (itemToDelete != null) {
            scope.launch {
                viewModel.deleteEntry(itemId)
                // No need to call getAllItems again, assuming deleteEntry updates the state
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(SharedRes.colors.white)),
    )
    {
        Column {
            App_Header(currentScreen, false)
            Spacer(modifier = Modifier.height(20.dp))

            if (updatedData.size > 0) {

                val revealedCardIds by viewModel.revealedCardIdsList.collectAsStateWithLifecycle()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(88.dp)
                        .padding(horizontal = 20.dp)
                        .background(
                            color = Color(0xFFE6F1FA),
                            shape = RoundedCornerShape(size = 20.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Take Diazepam Medicine\nin 2 hours!",
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 23.sp,
                            fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF3D7199),
                            textAlign = TextAlign.Center,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Next Remedies",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 32.sp,
                        fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF141750),
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {
                    items(updatedData.size) { medicine ->
                        Box(Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(88.dp)
                                    .padding(horizontal = 20.dp)
                                    .background(
                                        color = Color.Red,
                                        shape = RoundedCornerShape(size = 20.dp)
                                    ),

                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End,
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(45.dp)
                                        .padding(0.dp, 0.dp, 13.dp, 0.dp)
                                        .clickable {
                                            deletemyItem(updatedData[medicine].medicine_name)
                                        }
                                        .clip(shape = RoundedCornerShape(4.dp)),
                                    painter = painterResource(imageResource = SharedRes.images.trash),
                                    contentDescription = null
                                )
                            }
                            Draggable_item(
                                medicine_name = updatedData[medicine].medicine_name,
                                medicine_time_eng = updatedData[medicine].medicine_time_eng,
                                isRevealed = revealedCardIds.contains(updatedData[medicine].medicine_name),
                                cardOffset = ITEM_OFFSET,
                                onExpand = { viewModel.onItemExpanded(updatedData[medicine].medicine_name) },
                                onCollapse = { viewModel.onItemCollapsed(updatedData[medicine].medicine_name) },
                            )
                            Spacer(modifier = Modifier.height(10.dp)) // Adjust spacing as needed
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

            } else {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(SharedRes.images.circle_bg),
                            contentDescription = null, // Provide a suitable content description
                            modifier = Modifier.fillMaxSize()
                        )
                        Image(
                            painter = painterResource(SharedRes.images.drug_capsule),
                            contentDescription = null, // Provide a suitable content description
                            modifier = Modifier.fillMaxSize().padding(10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Let's start registering\nyour remedies!",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 25.sp,
                            fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF141750),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            navigator.navigate(NavigationScreens.MainScreen.route.plus("/screen_newmedicine"))
                        },
                        modifier = Modifier
                            .width(231.dp)
                            .height(56.dp)
                            .padding(horizontal = 20.dp)
                            .align(Alignment.CenterHorizontally)
                            .then(Modifier.clip(shape = RoundedCornerShape(16.dp)))
                            .background(colorResource(SharedRes.colors.ok_button_color)),

                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0.11f, 0.46f, 0.67f),
                            disabledBackgroundColor = Color(0.11f, 0.46f, 0.67f)
                        ),

                        ) {
                        Text("Register", fontSize = 18.sp, color = Color.White)
                    }

                }
            }

        }


    }


}
