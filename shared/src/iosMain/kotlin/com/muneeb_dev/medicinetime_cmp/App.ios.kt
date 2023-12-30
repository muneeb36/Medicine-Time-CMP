package com.muneeb_dev.medicinetime_cmp


import moe.tlaster.precompose.PreComposeApplication
import platform.UIKit.UIViewController


fun MainViewController(): UIViewController {

    return PreComposeApplication {
        App()
    }
}
