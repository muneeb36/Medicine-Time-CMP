package com.muneeb_dev.medicinetime_cmp.Expect_

import com.muneeb_dev.medicinetime_cmp.Expect_.DeviceType

class myAndroid : DeviceType {
    override val device: String = "Android"
}
actual fun getDevice(): DeviceType = myAndroid()
