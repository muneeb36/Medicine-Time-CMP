package com.muneeb_dev.medicinetime_cmp.Expect_

interface DeviceType {
    val device: String
}

expect fun getDevice(): DeviceType

