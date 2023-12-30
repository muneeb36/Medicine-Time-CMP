package com.muneeb_dev.medicinetime_cmp.Tools

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muneeb_dev.medicinetime_cmp.SharedRes
import com.muneeb_dev.medicinetime_cmp.Tools.MyTools.getImage
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import kotlin.math.roundToInt

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 8


const val ITEM_OFFSET = 200f


val gradientColors = listOf(
    Color(0xFFF5FAF7),
    Color(0xFFF0F0F0)
)


@Composable
fun AnimateVisibility(
    visible: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(animationSpec = tween(1000)) +
                expandVertically(
                    animationSpec = tween(1500)
                ),
        exit = fadeOut(animationSpec = tween(1000)) +
                shrinkVertically(
                    animationSpec = tween(1500)
                )
    ) {
        content.invoke()
    }
}

@Composable
fun CustomButton(modifier : Modifier , buttonText:String) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = buttonText,
            fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 23.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}



@Composable
fun Draggable_item(
    medicine_name:String,
    medicine_time_eng:String,
    isRevealed: Boolean,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
) {
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(transitionState, "cardTransition")
    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) cardOffset else 0f },

        )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(horizontal = 20.dp)
            .offset { -IntOffset(offsetTransition.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount >= MIN_DRAG_AMOUNT -> onCollapse()
                        dragAmount < -MIN_DRAG_AMOUNT -> onExpand()
                    }
                }
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors,
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                ),
                shape = RoundedCornerShape(size = 20.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // First Image
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                painter = painterResource(imageResource = getImage(medicine_name)),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(20.dp)) // Adjust spacing as needed

            // Text
            Text(
                text = medicine_name,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 25.sp,
                    fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                )
            )

            Spacer(modifier = Modifier.width(20.dp)) // Adjust spacing as needed

            Column {
                Text(
                    text = "Take at",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF5C6660),
                        textAlign = TextAlign.Right,
                    )
                )

                // Text
                Text(
                    text = medicine_time_eng,
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 25.sp,
                        fontFamily = fontFamilyResource(SharedRes.fonts.roboto_regular.roboto_regular),
                        fontWeight = FontWeight(500),
                        color = Color.Black,
                    )
                )
            }

        }
    }
}

