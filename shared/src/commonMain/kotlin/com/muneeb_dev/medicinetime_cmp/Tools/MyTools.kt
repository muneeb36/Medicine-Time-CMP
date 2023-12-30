package com.muneeb_dev.medicinetime_cmp.Tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.moriatsushi.insetsx.SystemBarsBehavior
import com.moriatsushi.insetsx.rememberWindowInsetsController
import com.muneeb_dev.medicinetime_cmp.SharedRes
import com.muneeb_dev.medicinetime_cmp.UI.Models.Medicine_Model
import dev.icerock.moko.resources.ImageResource

object MyTools {

    enum class currentDevice {
        iOS,
        Android,
    }


    @Composable
    fun show_N_hide_statusbar(isShhow: Boolean) {
        val windowInsetsController = rememberWindowInsetsController()

        LaunchedEffect(Unit) {
            // Hide the status bars
            windowInsetsController?.setIsStatusBarsVisible(isShhow)
            // Hide the navigation bars
            windowInsetsController?.setIsNavigationBarsVisible(isShhow)
            // Change an options for behavior when system bars are hidden
            windowInsetsController?.setSystemBarsBehavior(SystemBarsBehavior.Immersive)
        }
    }

    // Sample data class for demonstration
    val medicineList = listOf(
        Medicine_Model("Clonazepam", SharedRes.images.R13),
        Medicine_Model("Diazepam", SharedRes.images.Y04),
        Medicine_Model("Fenobarbital", SharedRes.images.B02),
        Medicine_Model("Ibuprofeno", SharedRes.images.V08),
        Medicine_Model("Fenobarbital2", SharedRes.images.Y15),
        Medicine_Model("Ibuprofeno2", SharedRes.images.R16),
        // Add more sample data as needed
    )

    fun getFormattedTime(hour: Int, minute: Int, is24Hour: Boolean , type:Int): String {
        val formattedHour = if (is24Hour) {
            hour % 24
        } else {
            when {
                hour == 0 -> 12
                hour > 12 -> hour - 12
                else -> hour
            }
        }

        val amPm = if (hour < 12) "AM" else " PM"

        if (type ==1) {
            return "$formattedHour hour : $minute minutes $amPm"
        }
        else {
            return "$formattedHour:$minute $amPm"
        }
    }



    fun getImage(mediname:String): ImageResource
    {
        return when (mediname)
        {
            "Clonazepam"-> SharedRes.images.R13
            "Diazepam" -> SharedRes.images.Y04
            "Fenobarbital" -> SharedRes.images.B02
            "Ibuprofeno" -> SharedRes.images.V08
            "Fenobarbital2" -> SharedRes.images.Y15
            "Ibuprofeno2" -> SharedRes.images.R16
            else -> {
                SharedRes.images.R13
            }
        }
    }


}