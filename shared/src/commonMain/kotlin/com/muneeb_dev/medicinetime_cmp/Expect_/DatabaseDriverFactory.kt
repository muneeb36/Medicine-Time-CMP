package com.muneeb_dev.medicinetime_cmp.Expect_

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}