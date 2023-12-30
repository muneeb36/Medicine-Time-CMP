package com.muneeb_dev.medicinetime_cmp.android

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muneeb_dev.medicinetime_cmp.MainAndroidView
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MedicienTime_APP()
                }
            }
        }
    }
}

@Composable
fun MedicienTime_APP() {
    MainAndroidView()
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MainAndroidView()
    }
}
