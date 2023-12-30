package com.muneeb_dev.medicinetime_cmp.Expect_

import com.muneeb_dev.medicinetime_cmp.Expect_.DeviceType
import platform.UIKit.UIDevice

class myIos: DeviceType {
    override val device: String = UIDevice.currentDevice.systemName()

}

actual fun getDevice(): DeviceType = myIos()
